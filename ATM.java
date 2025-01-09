package com.nit.pc.bank.user;

import java.io.InvalidClassException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.nit.pc.bank.exception.InsufficientFundsException;
import com.nit.pc.bank.exception.InvalidCardException;
import com.nit.pc.bank.exception.NegativeAmountException;
import com.nit.pc.bank.spec.ATMCard;

public class ATM {
	
	//HAS-A relation (Loose Coupling)
	private ATMCard card;

	public void insertCard(String cardName) throws InvalidClassException, InvalidCardException {
		
		//Reflection API code
		try {
			//for loading the card
			Class cls = Class.forName(cardName);
			
			//for instantiating the card
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Constructor constructor = cls.getDeclaredConstructor(long.class, String.class, double.class);
			Object 		obj 		= constructor.newInstance(1234, "Hari", 10000);
			
			//typecasting to card and storing in ATM instance variable
			if(obj instanceof ATMCard) {
				card = (ATMCard)obj;
				System.out.println(cls.getSimpleName() + " object is stored in ATM");
				
			}else {
				throw new InvalidCardException("Error: "+ cardName+ " is not a ATMCard");
			}
		}catch(ClassNotFoundException e) {
			throw new InvalidCardException("Error: "+ cardName+".class is not found");
			
		} catch (NoSuchMethodException | InstantiationException e) {
			throw new InvalidCardException("Error: "+ cardName+"(long,String,double) constructor is not found");

		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new InvalidCardException("Error: "+ cardName+"(long,String,double) constructor is not pubic");
			
		}
	}
	
	public void removeCard() {
		card = null;
	}
	
	public String deposit(double amt) throws NegativeAmountException {
		return card.deposit(amt);
	}
	
	public String withdraw(double amt) throws NegativeAmountException, InsufficientFundsException {
		
		return card.withdraw(amt); 
	}
	
	public void currentBalance() {
		card.currentBalance();
	}
	
}