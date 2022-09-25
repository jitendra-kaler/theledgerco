package com.theledgerco.parsers;

import com.theledgerco.model.ICommand;

/* Root parser for Loan, Payment & Balance Parsers */
public interface ICommandParser {
	
	public ICommand parse(String command);

}
