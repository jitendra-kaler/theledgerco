package com.theledgerco.parsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.theledgerco.model.ICommand;
import com.theledgerco.model.PaymentCommandImpl;

/* Loan Parser will parse the payment command and return payment command  object */
@Component
public class PaymentParserImpl implements ICommandParser {

	@Autowired
	PaymentCommandImpl paymentCommandImpl;
	
	@Override
	public ICommand parse(String command) {
		String[] tokens=command.split(" ");
		PaymentCommandImpl paymentCommandImpl=new PaymentCommandImpl();
		paymentCommandImpl.setBankName(tokens[1]);
		paymentCommandImpl.setBorrowerName(tokens[2]);
		paymentCommandImpl.setLumpSumAmount(Integer.parseInt(tokens[3]));
		paymentCommandImpl.setEmiNo(Integer.parseInt(tokens[4]));
		return paymentCommandImpl;
	}

}
