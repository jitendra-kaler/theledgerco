package com.theledgerco.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

/* This class represent the single Payment Command for handling Lump sum payment */
@Component
public class PaymentCommandImpl implements ICommand{
	
	@JsonProperty("BANK_NAME")
	private String bankName;
	@JsonProperty("BORROWER_NAME")
	private String borrowerName;
	@JsonProperty("LUMP_SUM_AMOUNT")
	private int lumpSumAmount;
	@JsonProperty("EMI_NO")
	private int emiNo;
	
	public PaymentCommandImpl() {
		
	}

	public PaymentCommandImpl(String bankName, String borrowerName, int lumpSumAmount, int emiNo) {
		super();
		this.bankName = bankName;
		this.borrowerName = borrowerName;
		this.lumpSumAmount = lumpSumAmount;
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

	public int getLumpSumAmount() {
		return lumpSumAmount;
	}

	public void setLumpSumAmount(int lumpSumAmount) {
		this.lumpSumAmount = lumpSumAmount;
	}

	public int getEmiNo() {
		return emiNo;
	}

	public void setEmiNo(int emiNo) {
		this.emiNo = emiNo;
	}

	@Override
	public String toString() {
		return "PaymentCommandImpl [BANK_NAME=" + bankName + ", BORROWER_NAME=" + borrowerName + ", LUMP_SUM_AMOUNT=" + lumpSumAmount
				+ ", EMI_NO=" + emiNo + "]";
	}

}
