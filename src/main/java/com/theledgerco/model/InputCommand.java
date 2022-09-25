package com.theledgerco.model;

import java.util.List;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

/* This class represent the actual input from HTTP request in JSON format 
 * and intermediate data structure for command input */
@Component
public class InputCommand {
	
	@JsonProperty("LOAN")
	private List<LoanCommandImpl> loanCommandImpls;
	@JsonProperty("PAYMENT")
	@Nullable
	private List<PaymentCommandImpl> paymentCommandImpls;
	@JsonProperty("BALANCE")
	private List<BalanceCommandImpl> balanceCommandImpls;
		
	public InputCommand() {
		super();
	}
	
	public InputCommand(List<LoanCommandImpl> loanCommandImpls, List<PaymentCommandImpl> paymentCommandImpls, List<BalanceCommandImpl> balanceCommandImpls) {
		super();
		this.loanCommandImpls = loanCommandImpls;
		this.paymentCommandImpls = paymentCommandImpls;
		this.balanceCommandImpls = balanceCommandImpls;
	}

	public List<LoanCommandImpl> getLoans() {
		return loanCommandImpls;
	}
	public void setLoans(List<LoanCommandImpl> loanCommandImpls) {
		this.loanCommandImpls = loanCommandImpls;
	}
	public List<PaymentCommandImpl> getPayments() {
		return paymentCommandImpls;
	}
	public void setPayments(List<PaymentCommandImpl> paymentCommandImpls) {
		this.paymentCommandImpls = paymentCommandImpls;
	}
	public List<BalanceCommandImpl> getBalances() {
		return balanceCommandImpls;
	}
	public void setBalances(List<BalanceCommandImpl> balanceCommandImpls) {
		this.balanceCommandImpls = balanceCommandImpls;
	}

	@Override
	public String toString() {
		return "\nInputCommand [loanCommandImpls=" + loanCommandImpls + ", \npaymentCommandImpls=" + paymentCommandImpls + ", \nbalanceCommandImpls=" + balanceCommandImpls + "]";
	}

}
