package com.theledgerco.model;

/* This class derived from Loan Command for further calculations */
public class LoanOutstanding {
	
	private double totalAmountToPay;
	private int emiInstallment;
	
	public LoanOutstanding(double totalAmountToPay, int emiInstallment) {
		super();
		this.totalAmountToPay = totalAmountToPay;
		this.emiInstallment = emiInstallment;
	}
	public double getTotalAmountToPay() {
		return totalAmountToPay;
	}
	public void setTotalAmountToPay(double totalAmountToPay) {
		this.totalAmountToPay = totalAmountToPay;
	}
	public int getEmiInstallment() {
		return emiInstallment;
	}
	public void setEmiInstallment(int emiInstallment) {
		this.emiInstallment = emiInstallment;
	}
	
	
	
}
