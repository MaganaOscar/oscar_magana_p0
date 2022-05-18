package service;

import java.util.List;

import exception.NoBankAccountException;
import exception.NoJointUserExistsException;
import exception.SystemException;
import model.BankAccountPOJO;
import model.UserPOJO;

public interface MainService {
	UserPOJO addUser(UserPOJO userPojo) throws SystemException;
	
	UserPOJO updateUser(UserPOJO userPojo) throws SystemException;
	
	UserPOJO getUser(int userID) throws SystemException;
	
	void deleteUser(int userID) throws SystemException;
	
	List<UserPOJO> getJointAccountUsers (int accountID) throws NoJointUserExistsException, SystemException;	
	
	int validateUser(String username, String password) throws SystemException;
	
	BankAccountPOJO addAccountToUser(String accountType, int userID) throws SystemException;
	
	BankAccountPOJO updateBalance(BankAccountPOJO account, double amount) throws SystemException;
	
	void deleteAccount(int accountID) throws SystemException;
	
	List<BankAccountPOJO> getUserBankAccounts(int userID) throws NoBankAccountException, SystemException;
	
	BankAccountPOJO getBankAccount(int accountID) throws SystemException;
}
