package com.nit.pc.bank.spec;

import com.nit.pc.bank.exception.InsufficientFundsException;
import com.nit.pc.bank.exception.NegativeAmountException;

public interface ATMCard {

	void currentBalance();

	String deposit(double amt) throws NegativeAmountException;

	String withdraw(double amt) throws NegativeAmountException, InsufficientFundsException;

}
