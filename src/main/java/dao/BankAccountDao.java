package dao;

import java.util.List;

import exception.NoBankAccountException;
import model.BankAccountPOJO;

public interface BankAccountDao {
	
	BankAccountPOJO addAccount(BankAccountPOJO accountPojo);
	
	BankAccountPOJO updateAccount(BankAccountPOJO accountPojo);
	
	void deleteAccount(int accountID);
	
	List<BankAccountPOJO> getUserBankAccounts(int userID, int accountID) throws NoBankAccountException;
	
	BankAccountPOJO getBankAccount(int accountID);
}
