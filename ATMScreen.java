package com.nit.pc.bank.main;

import java.io.InvalidClassException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.nit.pc.bank.exception.InsufficientFundsException;
import com.nit.pc.bank.exception.InvalidCardException;
import com.nit.pc.bank.exception.NegativeAmountException;
import com.nit.pc.bank.user.ATM;

public class ATMScreen {
	
	public static void main(String[] args) throws InvalidClassException {
		
		Scanner scn = new Scanner(System.in);
		
		ATM atm = new ATM();
		
		try {
			
			while(true) {
				System.out.print("\nInsert Card: ");
				String cardName = scn.next();
				atm.insertCard(cardName);
				System.out.println();

				String option = "N";
				
				dowhile:do {
					System.out.println("\nChoose one option");
					System.out.println(" 1. Deposit");
					System.out.println(" 2. Withdraw");
					System.out.println(" 3. CurrentBalance");
					System.out.println(" 4. Exit");
					
					int choice ;
					while(true) {
						try {
							System.out.print("\nEnter option: ");
							choice = scn.nextInt(); scn.nextLine();
							break;
						}catch(InputMismatchException e) {
							scn.nextLine();
							System.out.println("Error: Enter only integer");
						}
					}//while end
					
					switch(choice) {
						case 1: { //Deposit operation
							while(true) {
								try {
									System.out.print("\nEnter deposit amount: ");
									String message = atm.deposit(scn.nextDouble());
									System.out.println(message);
									break;	
								} catch(InputMismatchException e) {
									scn.nextLine();
									System.out.println("Error: Enter only number");
								} catch (NegativeAmountException e) {
									System.out.println("Error: "+ e.getMessage());
								}		
							}
							break;
						}//deposit case end
						
						case 2:{ //withdraw operation
							while(true) {	
								try {
									
									System.out.print("\nEnter withdraw amount: ");
									String message = atm.withdraw(scn.nextDouble()); scn.nextLine();
									System.out.println(message);
									break;
								} catch(InputMismatchException e) {
									scn.nextLine();
									System.out.println("Error: Enter only number");
									
								} catch (NegativeAmountException | InsufficientFundsException e) {
									System.out.println("Error: "+ e.getMessage());
								}
							}//while(true) end
							
							break;	
						}//withdraw end
						
						case 3: {//balance enquire
							atm.currentBalance();
							break;
						}
						
						case 4: {//exit
							break dowhile;
						}
						
						default: {
							System.out.println("Invalid option");
						}
					}//switch end
					
					System.out.print("\nDo you want to continue(Y/N)?: ");
					option = scn.next();
					
				}while(option.equalsIgnoreCase("Y"));
				
				//removing ATMCard object from ATM
				atm.removeCard();
				
				System.out.println();
				System.out.println("======================================");
				System.out.println("           Take your card\n           ");
				System.out.println("        Thank You, Visit Agian        ");
				System.out.println("=======================================");
				
			}//while(true) end for next customer
			
		}catch(InvalidCardException e) {
			System.out.println(e.getMessage());
		}
		
	}
}