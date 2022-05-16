package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import exception.NoBankAccountException;
import model.BankAccountPOJO;

public class BankAccountDatabaseImpl implements BankAccountDao {

	@Override
	public BankAccountPOJO addAccountToUser(BankAccountPOJO accountPojo, int userID) {
		Connection conn = null;
		try {
			conn = DBUtil.makeConnection();
			Statement stmt = conn.createStatement();
			String query1 = "INSERT INTO accounts (type, balance) VALUES ('" + accountPojo.getAccountType() + "', '" 
					+ accountPojo.getBalance() + "') returning account_id;";
			
			ResultSet resultSet = stmt.executeQuery(query1);
			resultSet.next();
			accountPojo.setAccountID(1);
			
			
			
		} catch(SQLException e) {
			e.printStackTrace();
			//throw new exception
		}
		return accountPojo;
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
