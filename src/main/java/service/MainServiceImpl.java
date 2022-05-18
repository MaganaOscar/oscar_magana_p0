package service;

import java.util.List;

import dao.BankAccountDao;
import dao.BankAccountDatabaseImpl;
import dao.UserDao;
import dao.UserDaoDatabaseImpl;
import exception.NoBankAccountException;
import exception.NoJointUserExistsException;
import exception.SystemException;
import model.BankAccountPOJO;
import model.UserPOJO;

public class MainServiceImpl implements MainService {
	UserDao userDao;
	BankAccountDao accountDao;
	
	public MainServiceImpl() {
		userDao = new UserDaoDatabaseImpl();
		accountDao = new BankAccountDatabaseImpl();
	}

	@Override
	public UserPOJO addUser(UserPOJO userPojo) throws SystemException{
		return userDao.addUser(userPojo);
	}

	@Override
	public UserPOJO updateUser(UserPOJO userPojo) throws SystemException{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public UserPOJO getUser(int userID) throws SystemException{
		return userDao.getUser(userID);
	}

	@Override
	public void deleteUser(int userID) throws SystemException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<UserPOJO> getJointAccountUsers(int accountID) throws NoJointUserExistsException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int validateUser(String username, String password) throws SystemException {
		// TODO Auto-generated method stub
		return userDao.validateUser(username, password);
	}

	@Override
	public BankAccountPOJO addAccountToUser(String accountType, int userID) throws SystemException {
		return accountDao.addAccountToUser(accountType, userID);
	}

	@Override
	public BankAccountPOJO updateBalance(BankAccountPOJO account, double amount) throws SystemException {
		return accountDao.updateBalance(account, amount);
	}

	@Override
	public void deleteAccount(int accountID) throws SystemException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BankAccountPOJO> getUserBankAccounts(int userID) throws NoBankAccountException, SystemException {
		return accountDao.getUserBankAccounts(userID);
	}

	@Override
	public BankAccountPOJO getBankAccount(int accountID) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

}
