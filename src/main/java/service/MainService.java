package service;

import java.util.List;

import exception.NoBankAccountException;
import exception.NoJointUserExistsException;
import model.BankAccountPOJO;
import model.UserPOJO;

public interface MainService {
	UserPOJO addUser(UserPOJO userPojo);
	
	UserPOJO updateUser(UserPOJO userPojo);
	
	void deleteUser(int userID);
	
	List<UserPOJO> getJointAccountUsers (int accountID) throws NoJointUserExistsException;	
	
	UserPOJO getUser(int userID);
	
	BankAccountPOJO addAccountToUser(BankAccountPOJO accountPojo, int userID);
	
	BankAccountPOJO updateAccount(BankAccountPOJO accountPojo);
	
	void deleteAccount(int accountID);
	
	List<BankAccountPOJO> getUserBankAccounts(int userID, int accountID) throws NoBankAccountException;
	
	BankAccountPOJO getBankAccount(int accountID);
}
