package com.theledgerco.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.theledgerco.model.BalanceCommandImpl;
import com.theledgerco.model.ICommand;
import com.theledgerco.model.InputCommand;
import com.theledgerco.model.LoanCommandImpl;
import com.theledgerco.model.LoanOutstanding;
import com.theledgerco.model.CommandOutput;
import com.theledgerco.model.PaymentCommandImpl;
import com.theledgerco.parsers.BalanceParserImpl;
import com.theledgerco.parsers.ICommandParser;
import com.theledgerco.parsers.LoanParserImpl;
import com.theledgerco.parsers.PaymentParserImpl;

/* Service class for business logic */
@Service
public class GeektrustService {
	
	final Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	@Qualifier("loanParserImpl")
	ICommandParser loanParserImpl;
	
	@Autowired
	@Qualifier("paymentParserImpl")
	ICommandParser paymentParserImpl;
	
	@Autowired
	@Qualifier("balanceParserImpl")
	ICommandParser balanceParserImpl;
	
	@Autowired
	@Qualifier("loanCommandImpl")
	ICommand loanCommandImpl;
	
	@Autowired
	@Qualifier("paymentCommandImpl")
	ICommand paymentCommandImpl;
	
	@Autowired
	@Qualifier("balanceCommandImpl")
	ICommand balanceCommandImpl;
	
	@Autowired
	InputCommand inputCommand;
	
	/* This method will parse the text format commands 
	 * invoke actual logic to calculate output */
	public void parseFile(InputStream inputStream) {
		List<CommandOutput> balanceOutput = null;
	    try {
	        byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
	        String data = new String(bdata, StandardCharsets.UTF_8);
	        
	        String[] lines=data.split("\n");
	        InputCommand inputCommand = parseCommand(lines);
	        balanceOutput=getCommandOutput(inputCommand);
	    } catch (IOException e) {
	      LOGGER.error("IOException", e);
	    }
	}

	/* This method will parse command one by one using appropriate parsers */
	private InputCommand parseCommand(String[] lines) {
		List<LoanCommandImpl> loanCommandList=new ArrayList<>();
        List<PaymentCommandImpl> paymentCommandList=new ArrayList<>();
        List<BalanceCommandImpl> balanceCommandList=new ArrayList<>();
		for (String line : lines) {
			if(line.startsWith("LOAN")) {
				loanCommandImpl = loanParserImpl.parse(line);
				loanCommandList.add((LoanCommandImpl) loanCommandImpl);
			} else if(line.startsWith("PAYMENT")){
				paymentCommandImpl = paymentParserImpl.parse(line);
				paymentCommandList.add((PaymentCommandImpl) paymentCommandImpl);
			} else if(line.startsWith("BALANCE")){
				balanceCommandImpl = balanceParserImpl.parse(line);
				balanceCommandList.add((BalanceCommandImpl) balanceCommandImpl);
			} else {
				LOGGER.error("Unidentified command encountered: "+ line);
			}
		}
		inputCommand.setLoans(loanCommandList);
		inputCommand.setPayments(paymentCommandList);
		inputCommand.setBalances(balanceCommandList);
		return inputCommand;
	}
	
	/* This method is the center point for algorithm which find out 
	 * total loan outstanding and prepare desired output */
	public List<CommandOutput> getCommandOutput(InputCommand inputCommand) {	
		List<LoanCommandImpl> loanCommandImpls = inputCommand.getLoans();
		Map<String, LoanOutstanding> totalOutstandings = getTotalOutstanding(loanCommandImpls);
		List<CommandOutput> balanceOutput = getRemainingOutstanding(inputCommand, totalOutstandings);		
		LOGGER.info(balanceOutput.toString());
		return balanceOutput;
	}
	
	/* This method will calculate total loan outstanding in terms of 
	 * interest, total EMIs and monthly installment */
	private Map<String, LoanOutstanding> getTotalOutstanding(List<LoanCommandImpl> loanCommandImpls) {
		Map<String,LoanOutstanding> loanOutstandingMap = new HashMap<>();
		for (LoanCommandImpl loanCommandImpl : loanCommandImpls) {
			//I (Interest) = P(Principal) * N(Number of years) * R (Rate of interest)
			int interest = (int) (loanCommandImpl.getPrincipal() * loanCommandImpl.getLoanPeriodInYears() * (loanCommandImpl.getRateOfInterest() * 0.01));
			// A = P (Principal) + I (Interest)
			double totalAmountToPay = loanCommandImpl.getPrincipal() + interest;
			// EMIs paid monthly
			int totalEMIs = loanCommandImpl.getLoanPeriodInYears() * 12;
			int emiAmount =	(int) Math.ceil(totalAmountToPay/totalEMIs);
			
			LoanOutstanding loanOutstanding = new LoanOutstanding(totalAmountToPay, emiAmount);
			loanOutstandingMap.put(loanCommandImpl.getBankName()+loanCommandImpl.getBorrowerName(), loanOutstanding);					
		}
		return loanOutstandingMap;
	}
	
	/* This method calculate remaing outstanding after 
	 * lump sum payment and prepare desired output */
	private List<CommandOutput> getRemainingOutstanding(InputCommand inputCommand, Map<String, LoanOutstanding> totalOutstandings) {
		List<CommandOutput> balanceOutput = new ArrayList<>();
		List<PaymentCommandImpl> paymentCommandImpls = inputCommand.getPayments();
		List<BalanceCommandImpl> balanceCommandImpls = inputCommand.getBalances();
		for (BalanceCommandImpl balanceCommandImpl : balanceCommandImpls) {
			LoanOutstanding loanOutstanding = totalOutstandings .get(balanceCommandImpl.getBankName()+balanceCommandImpl.getBorrowerName());
			//EMI Paid before checking outstanding (EMI Amount Paid = EMI Installment * EMI no)
			int emiAmountPaid =  loanOutstanding.getEmiInstallment() * balanceCommandImpl.getEmiNo();			
			int totalAmountPaid = 0;
			
			//If lump sum payment done before checking outstanding
			if(paymentCommandImpls!=null) {
				for (PaymentCommandImpl paymentCommandImpl : paymentCommandImpls) {
					if(paymentCommandImpl.getBankName().equals(balanceCommandImpl.getBankName()) && paymentCommandImpl.getBorrowerName().equals(balanceCommandImpl.getBorrowerName()) && paymentCommandImpl.getEmiNo()<=balanceCommandImpl.getEmiNo()) {
						totalAmountPaid = emiAmountPaid + paymentCommandImpl.getLumpSumAmount();
					}
				}
			}
			//Handing condition if lump sum amount not paid
			if(totalAmountPaid==0)
				totalAmountPaid=emiAmountPaid;
			
			//EMI Left = (Total Outstanding Amount - Total amount paid including lump sum)/Monthly Installment
			int emisLeft = (int) Math.ceil((loanOutstanding.getTotalAmountToPay() - totalAmountPaid)/loanOutstanding.getEmiInstallment());		
			
			CommandOutput commandOutput = new CommandOutput(balanceCommandImpl.getBankName(), balanceCommandImpl.getBorrowerName(), totalAmountPaid, emisLeft);
			balanceOutput.add(commandOutput);
		}
		return balanceOutput;
	}

}
