package com.theledgerco.model;

import org.springframework.stereotype.Component;

/* This class represent the single Command output */
@Component
public class CommandOutput {
	
	private String bankName;
	private String borrowerName;
	private int amountPaid;
	private int emisLeft;
	
	public CommandOutput() {
	}
	
	public CommandOutput(String bankName, String borrowerName, int amountPaid, int emisLeft) {
		super();
		this.bankName = bankName;
		this.borrowerName = borrowerName;
		this.amountPaid = amountPaid;
		this.emisLeft = emisLeft;
	}

	public String getBankName() {
		return bankName;
	}
	
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public int getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}

	public int getEmisLeft() {
		return emisLeft;
	}

	public void setEmisLeft(int emisLeft) {
		this.emisLeft = emisLeft;
	}

	@Override
	public String toString() {
		return "\nBANK_NAME=" + bankName + ", BORROWER_NAME=" + borrowerName + ", AMOUNT_PAID=" + amountPaid
				+ ", NO_OF_EMIS_LEFT=" + emisLeft;
	}
	

}
