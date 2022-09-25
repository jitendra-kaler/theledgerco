package com.theledgerco.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theledgerco.model.BalanceCommandImpl;
import com.theledgerco.model.CommandOutput;
import com.theledgerco.model.InputCommand;
import com.theledgerco.model.LoanCommandImpl;
import com.theledgerco.model.PaymentCommandImpl;
import com.theledgerco.service.GeektrustService;

@WebMvcTest(GeektrustResource.class)
public class GeektrustResourceApplicationTests {
	
	@Autowired
	MockMvc mockMvc;

	@MockBean
	private GeektrustService service;

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private InputCommand input;
	
	@Test
	public void getCommandOutputTest() throws Exception {
		InputCommand input = getMockInputCommand();
		List<CommandOutput> output = getMockCommandOutput();

		when(service.getCommandOutput(input)).thenReturn(output);

		this.mockMvc.perform(post("/geektrust/command-output").content(this.mapper.writeValueAsString(output))
				.contentType(MediaType.APPLICATION_JSON)

				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	private List<CommandOutput> getMockCommandOutput() {
		List<CommandOutput> output = new ArrayList<>();
		CommandOutput co=new CommandOutput("IDIDI", "Dale", 1326, 9);
		output.add(co);
		co=new CommandOutput("IDIDI", "Dale", 3652, 4);
		output.add(co);
		co=new CommandOutput("UON", "Shelly", 15856, 3);
		output.add(co);
		co=new CommandOutput("MBI", "Harry", 9044, 10);
		output.add(co);
		
		return output;
	}

	private InputCommand getMockInputCommand() {
		
		List<LoanCommandImpl> loanCommandList=new ArrayList<>();
        List<PaymentCommandImpl> paymentCommandList=new ArrayList<>();
        List<BalanceCommandImpl> balanceCommandList=new ArrayList<>();
        
        LoanCommandImpl loanCommand=new LoanCommandImpl("IDIDI", "Dale", 5000, 1, 6);
        loanCommandList.add(loanCommand);
        loanCommand=new LoanCommandImpl("MBI", "Harry", 10000, 3, 7);
        loanCommandList.add(loanCommand);
        loanCommand=new LoanCommandImpl("UON", "Shelly", 15000, 2, 9);
        loanCommandList.add(loanCommand);
        
        PaymentCommandImpl paymentCommand=new PaymentCommandImpl("IDIDI", "Dale", 1000, 5);
        paymentCommandList.add(paymentCommand);
        paymentCommand=new PaymentCommandImpl("MBI", "Harry", 5000, 10);
        paymentCommandList.add(paymentCommand);
        paymentCommand=new PaymentCommandImpl("UON", "Shelly", 7000, 12);
        paymentCommandList.add(paymentCommand);
        
        BalanceCommandImpl balanceCommand = new BalanceCommandImpl("IDIDI", "Dale", 3);
        balanceCommandList.add(balanceCommand);
        balanceCommand = new BalanceCommandImpl("IDIDI", "Dale", 6);
        balanceCommandList.add(balanceCommand);
        balanceCommand = new BalanceCommandImpl("UON", "Shelly", 12);
        balanceCommandList.add(balanceCommand);
        balanceCommand = new BalanceCommandImpl("MBI", "Harry", 12);
        balanceCommandList.add(balanceCommand);
        
        input.setLoans(loanCommandList);
        input.setPayments(paymentCommandList);
        input.setBalances(balanceCommandList);
        
		return input;
	}

}
