package com.theledgerco.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

/* This class represent the single Balance Command */
@Component
public class BalanceCommandImpl implements ICommand  {
	
	@JsonProperty("BANK_NAME")
	private String bankName;
	@JsonProperty("BORROWER_NAME")
	private String borrowerName;
	@JsonProperty("EMI_NO")
	private int emiNo;
	
	public BalanceCommandImpl() {
		
	}

	public BalanceCommandImpl(String bankName, String borrowerName, int emiNo) {
		super();
		this.bankName = bankName;
		this.borrowerName = borrowerName;
		this.emiNo = emiNo;
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

	public int getEmiNo() {
		return emiNo;
	}

	public void setEmiNo(int emiNo) {
		this.emiNo = emiNo;
	}

	@Override
	public String toString() {
		return "BalanceCommandImpl [BANK_NAME=" + bankName + ", BORROWER_NAME=" + borrowerName + ", EMI_NO=" + emiNo + "]";
	}

}
