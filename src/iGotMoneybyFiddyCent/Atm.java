package iGotMoneybyFiddyCent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import utilityForHire.UtilityNumberFormat;
import utilityForHire.UtilityStringNScan;

public abstract class Atm {
	private static ArrayList <String> transactions = new ArrayList();
	private static Account account = new Account();
	private static Scanner strScan = new Scanner(System.in);
	private static Scanner numScan = new Scanner(System.in);
	private double nada = 0.0;
	public Atm(){
		
	}
	public static void deposit(){
		boolean keepLooping = false;
		double dep = 0;
		do{
			dep = UtilityStringNScan.doubleCheck("How much would you like to deposit?", keepLooping, numScan);
			if(dep<=0){
				System.out.println("You can't deposit $0. Please try again.");
			}	
		}while(dep<=0.0);
			
		
		account.setBalance(account.getBalance()+dep);
		LocalDateTime moment = LocalDateTime.now();
		LocalDate date = moment.toLocalDate();
		LocalTime time = moment.toLocalTime();
		transactions.add(String.format("%s deposited %s from %s at %s %s.", account.getName(), UtilityNumberFormat.toCurrency(dep), account.getAccountID(), date.toString(), time.toString().substring(0,5)));
		System.out.print(UtilityStringNScan.consoleTextFormatter(String.format("%s deposited %s from %s at %s %s. You have %s remaining.\n", account.getName(), UtilityNumberFormat.toCurrency(dep), account.getAccountID(), date.toString(), time.toString().substring(0,5), UtilityNumberFormat.toCurrency(account.getBalance())), 5));
	}
	public static void withdraw(){
		boolean keepLooping = false;
		double wit = 0;
		do{
			wit = UtilityStringNScan.doubleCheck("How much would you like to withdraw?", keepLooping, numScan);
			if(wit> account.getBalance()){
				System.out.println(UtilityStringNScan.consoleTextFormatter(String.format("You only have %s in yout account. You can't withdraw %s. Please try again.", UtilityNumberFormat.toCurrency(account.getBalance()), UtilityNumberFormat.toCurrency(wit)),5));	
			}
		}while(wit> account.getBalance());
		account.setBalance(account.getBalance()-wit);
		LocalDateTime moment = LocalDateTime.now();
		LocalDate date = moment.toLocalDate();
		LocalTime time = moment.toLocalTime();
		transactions.add(String.format("%s withdrew %s from %s at %s %s.", account.getName(), UtilityNumberFormat.toCurrency(wit), account.getAccountID(), date.toString(), time.toString().substring(0,5)));
		System.out.print(UtilityStringNScan.consoleTextFormatter(String.format("%s withdrew %s from %s at %s %s. You have %s remaining.\n", account.getName(), UtilityNumberFormat.toCurrency(wit), account.getAccountID(), date.toString(), time.toString().substring(0,5), UtilityNumberFormat.toCurrency(account.getBalance())),5));
	}
	public static void checkAccount(){
		LocalDateTime moment = LocalDateTime.now();
		LocalDate date = moment.toLocalDate();
		LocalTime time = moment.toLocalTime();
		transactions.add(String.format("%s checked account %s's balance of %s at %s %s.", account.getName(), account.getAccountID(), UtilityNumberFormat.toCurrency(account.getBalance()), date.toString(), time.toString().substring(0,5)));
		System.out.print(UtilityStringNScan.consoleTextFormatter(String.format("%s checked account %s's balance of %s at %s %s.\n", account.getName(), account.getAccountID(), UtilityNumberFormat.toCurrency(account.getBalance()), date.toString(), time.toString().substring(0,5)), 5));
	}
	public static void transactionSummary(){
		System.out.println(UtilityStringNScan.consoleTextFormatter(UtilityStringNScan.objectPrinter(transactions),5));
	}
	public static void startAtm(){
		String yesOrNo = "";
		boolean again = false;
		boolean keepLooping=false;
		//int selection=numScan.nextInt();
		do{
			int selection = 0;
			do{
				selection = UtilityStringNScan.intCheck("Welcome to the ATM\nPress 1 to Deposit Cash\nPress 2 to Withdraw Cash\nPress 3 to Check your Balance\nPress 4 for a Receipt", keepLooping, numScan);
				if(!(selection==1||selection==2||selection==3||selection==4)){
					 System.out.println("I'm sorry you didn't press a valid number. Please try agqain"); 
				}
			}while(!(selection==1||selection==2||selection==3||selection==4));
			//selection = numScan.nextInt();
			switch(selection){
			case 1: deposit();
			break;
			case 2: withdraw();
			break;
			case 3: checkAccount();
			break;
			case 4: transactionSummary();
			break;
			}
		}while(UtilityStringNScan.letsGoAgain(yesOrNo, again, strScan));
	}
	
}
