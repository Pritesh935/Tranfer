package com.nit.pc.bank.imple;

import com.nit.pc.bank.exception.InsufficientFundsException;
import com.nit.pc.bank.exception.NegativeAmountException;
import com.nit.pc.bank.spec.ATMCard;

public class HDFCBankCard implements ATMCard {
	
	private long accNum;
	private String accHName;
	private double balance;
	
	static {
		System.out.println("HDFCBankCard class is loaded into JVM");
	}
	
	public HDFCBankCard(long accNum, String accHName, double balance) {
		this.accNum = accNum;
		this.accHName = accHName;
		this.balance = balance;
		
		System.out.println("HDFCBankCard class is instantiated");
		
	}

	@Override
	public String deposit(double amt) throws NegativeAmountException {
		
		if(amt <= 0) 
			throw new NegativeAmountException("Amount can not be -ve or zero");
		
		this.balance = this.balance + amt;
		return "  "+amt + " is credited to the HDFC Bank Account " + accNum +
			   "\n  Available balance is "+ balance	 ;
	}
	
	@Override
	public String withdraw(double amt) throws NegativeAmountException, InsufficientFundsException {
		
		if(amt<=0)
			throw new NegativeAmountException("Amount can not be -ve or zero");

		if(amt>balance)
			throw new InsufficientFundsException("Insufficient Funds");
			
		this.balance = this.balance - amt;	
		return "  "+amt + " is debited from HDFC Bank Account "+ accNum +
				"\n  Available balance is "+ balance;
	}
	
	@Override
	public void currentBalance() {
		System.out.println("  The current balance in HDFC Bank account is "+ balance);
	}

	@Override
	public String toString() {
		return "  HDFCBankCard(accNum=" + accNum + ", accHName=" + accHName + ", balance=" + balance + ")";
	}
	
}