package model;

public class BankAccountPOJO {
	private int accountID;
	private char accountType;
	private double balance;
	
	public BankAccountPOJO() {
		super();
	}
	
	public BankAccountPOJO(int accountID, char accountType, double balance) {
		super();
		this.accountID = accountID;
		this.accountType = accountType;
		this.balance = balance;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public char getAccountType() {
		return accountType;
	}

	public void setAccountType(char accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "BankAccountPOJO [accountID=" + accountID + ", accountType=" + accountType + ", balance=" + balance
				+ "]";
	}
	
}
