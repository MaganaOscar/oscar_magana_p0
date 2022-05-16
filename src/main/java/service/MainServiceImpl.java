package service;

import java.util.List;

import exception.NoBankAccountException;
import exception.NoJointUserExistsException;
import model.BankAccountPOJO;
import model.UserPOJO;

public class MainServiceImpl implements MainService {

	@Override
	public UserPOJO addUser(UserPOJO userPojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserPOJO updateUser(UserPOJO userPojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(int userID) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<UserPOJO> getJointAccountUsers(int accountID) throws NoJointUserExistsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserPOJO getUser(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccountPOJO addAccountToUser(BankAccountPOJO accountPojo, int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccountPOJO updateAccount(BankAccountPOJO accountPojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAccount(int accountID) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BankAccountPOJO> getUserBankAccounts(int userID, int accountID) throws NoBankAccountException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccountPOJO getBankAccount(int accountID) {
		// TODO Auto-generated method stub
		return null;
	}

}
