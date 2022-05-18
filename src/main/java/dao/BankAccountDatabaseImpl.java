package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exception.NoBankAccountException;
import exception.SystemException;
import model.BankAccountPOJO;

public class BankAccountDatabaseImpl implements BankAccountDao {

	@Override
	public BankAccountPOJO addAccountToUser(String accountType, int userID) throws SystemException {
		Connection conn = null;
		try {
			conn = DBUtil.makeConnection();
			Statement stmt1 = conn.createStatement();
			String query1 = "INSERT INTO bank_accounts (account_type, balance) VALUES ('" + accountType + "', " 
					+ "0) returning account_id;";
			
			ResultSet resultSet = stmt1.executeQuery(query1);
			resultSet.next();
			int accountID = resultSet.getInt(1);

			Statement stmt2 = conn.createStatement();
			String query2 = "INSERT INTO users_bank_accounts (user_id, account_id) VALUES (" + userID 
					+ ", " + accountID + ");";
			stmt2.executeUpdate(query2);
			
			BankAccountPOJO newAccount = new BankAccountPOJO();
			newAccount.setAccountID(accountID);
			newAccount.setAccountType(accountType);
			newAccount.setBalance(0);
			return newAccount;
			
		} catch(SQLException e) {
			e.printStackTrace();
			throw new SystemException();
		}
	}

	@Override
	public BankAccountPOJO updateBalance(BankAccountPOJO account, double amount) throws SystemException {
		Connection conn = null;
		try {
			conn = DBUtil.makeConnection();
			Statement stmt = conn.createStatement();
			
			double newBalance = amount + account.getBalance();
			account.setBalance(newBalance);
			
			String query = "UPDATE bank_accounts SET balance = " + newBalance + "WHERE account_id = " + account.getAccountID();
			stmt.executeUpdate(query);
			
			
		} catch(SQLException e) {
			e.printStackTrace();
			throw new SystemException();
		}
		return account;
	}

	@Override
	public void deleteAccount(int accountID) throws SystemException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BankAccountPOJO> getUserBankAccounts(int userID) throws NoBankAccountException, SystemException {
		Connection conn = null;
		try {
			List<BankAccountPOJO>  userBankAccounts = new ArrayList<BankAccountPOJO>();
			conn = DBUtil.makeConnection();
			Statement stmt = conn.createStatement();
			String query = "SELECT bank_accounts.account_id, account_type, balance FROM users_bank_accounts JOIN bank_accounts ON user_id = " 
					+ userID + " AND users_bank_accounts.account_id = bank_accounts.account_id ORDER BY bank_accounts.account_id ASC;";
			ResultSet resultSet = stmt.executeQuery(query);
			
			if(!resultSet.isBeforeFirst()) {
				throw new NoBankAccountException();
			} else {
				while(resultSet.next()) {
					userBankAccounts.add(new BankAccountPOJO(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3)));
				}
				return userBankAccounts;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			throw new SystemException();
		}
	}
}
