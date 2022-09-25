package com.theledgerco.parsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.theledgerco.model.BalanceCommandImpl;
import com.theledgerco.model.ICommand;

/* Balance Parser will parse the balance command and return balance command  object */
@Component
public class BalanceParserImpl implements ICommandParser {

	@Autowired
	BalanceCommandImpl balanceCommandImpl;
	
	@Override
	public ICommand parse(String command) {
		String[] tokens=command.split(" ");	
		BalanceCommandImpl balanceCommandImpl=new BalanceCommandImpl();
		balanceCommandImpl.setBankName(tokens[1]);
		balanceCommandImpl.setBorrowerName(tokens[2]);
		balanceCommandImpl.setEmiNo(Integer.parseInt(tokens[3]));
		return balanceCommandImpl;
	}

}
