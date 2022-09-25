package com.theledgerco.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

/* This class represent the single Loan Command */
@Component
public class LoanCommandImpl implements ICommand{
		
	@JsonProperty("BANK_NAME")
	private String bankName;
	@JsonProperty("BORROWER_NAME")
	private String borrowerName;
	@JsonProperty("PRINCIPAL")
	private int principal;
	@JsonProperty("NO_OF_YEARS")
	private int loanPeriodInYears;
	@JsonProperty("RATE_OF_INTEREST")
	private int rateOfInterest;
	
	public LoanCommandImpl() {
		super();
	}
	
	public LoanCommandImpl(String bankName, String borrowerName, int principal, int loanPeriodInYears,
			int rateOfInterest) {
		super();
		this.bankName = bankName;
		this.borrowerName = borrowerName;
		this.principal = principal;
		this.loanPeriodInYears = loanPeriodInYears;
		this.rateOfInterest = rateOfInterest;
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
	public int getPrincipal() {
		return principal;
	}
	public void setPrincipal(int principal) {
		this.principal = principal;
	}
	public int getLoanPeriodInYears() {
		return loanPeriodInYears;
	}
	public void setLoanPeriodInYears(int loanPeriodInYears) {
		this.loanPeriodInYears = loanPeriodInYears;
	}
	public int getRateOfInterest() {
		return rateOfInterest;
	}
	public void setRateOfInterest(int rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}

	@Override
	public String toString() {
		return "LoanCommandImpl [BANK_NAME=" + bankName + ", BORROWER_NAME=" + borrowerName + ", PRINCIPAL=" + principal
				+ ", NO_OF_YEARS=" + loanPeriodInYears + ", RATE_OF_INTEREST=" + rateOfInterest + "]";
	}	

}
