package dao;

import java.util.List;

import exception.NoBankAccountException;
import exception.SystemException;
import model.BankAccountPOJO;

public interface BankAccountDao {
	
	BankAccountPOJO addAccountToUser(String accountType, int userID) throws SystemException;
	
	BankAccountPOJO updateBalance(BankAccountPOJO account, double amount) throws SystemException;
	
	void deleteAccount(int accountID) throws SystemException;
	
	List<BankAccountPOJO> getUserBankAccounts(int userID) throws NoBankAccountException, SystemException;
}
