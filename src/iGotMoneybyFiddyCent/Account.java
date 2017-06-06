package iGotMoneybyFiddyCent;

import utilityForHire.UtilityNumberFormat;

public class Account {
	private int accountID = 0;;
	private String name = "";
	private double balance = 0.0;
	
	public Account(int aID, String nam, double bal){
		accountID = aID;
		name = nam;
		balance = bal;
	}
	public Account(){
		accountID = 1234;
		name = "John Doe";
		balance = 100.00;
	}
	
	public int getAccountID(){
		return accountID;
	}
	
	public String getName(){
		return name;
	}
	
	public double getBalance(){
		return balance;
	}
	
	public void setAccountID(int aID){
		accountID=aID;
	}

	public void setName(String nam){
		name = nam;
	}

	public void setBalance(double bal){
		balance = bal;
	}
	
	public String toString(){
	return String.format("ID: %s\tName: %s\t%s\tBalance: %s", accountID, name, UtilityNumberFormat.toCurrency(balance));
	}

}
