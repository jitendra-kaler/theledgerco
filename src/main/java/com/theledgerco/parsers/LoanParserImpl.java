package com.theledgerco.parsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.theledgerco.model.ICommand;
import com.theledgerco.model.LoanCommandImpl;

/* Loan Parser will parse the loan command and return loan command object */
@Component
public class LoanParserImpl implements ICommandParser {
	
	@Autowired
	LoanCommandImpl loanCommandImpl;

	@Override
	public ICommand parse(String command) {
		String[] tokens=command.split(" ");
		LoanCommandImpl loanCommandImpl=new LoanCommandImpl();
		loanCommandImpl.setBankName(tokens[1]);
		loanCommandImpl.setBorrowerName(tokens[2]);
		loanCommandImpl.setPrincipal(Integer.parseInt(tokens[3]));
		loanCommandImpl.setLoanPeriodInYears(Integer.parseInt(tokens[4]));
		loanCommandImpl.setRateOfInterest(Integer.parseInt(tokens[5]));
		return loanCommandImpl;
	}

}
