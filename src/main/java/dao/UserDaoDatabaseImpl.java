package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import exception.NoJointUserExistsException;
import exception.SystemException;
import model.UserPOJO;

public class UserDaoDatabaseImpl implements UserDao {

	@Override
	public UserPOJO addUser(UserPOJO userPojo) throws SystemException{
		Connection conn = null;
		try {
			conn = DBUtil.makeConnection();
			Statement stmt = conn.createStatement();
			String query = "INSERT INTO users(username, password) VALUES ('" + userPojo.getUsername() + "', crypt('" 
					+ userPojo.getPassword() + "', gen_salt('bf'))) returning user_id;";
			
			ResultSet resultSet = stmt.executeQuery(query);
			resultSet.next();
			userPojo.setUserID(resultSet.getInt(1));
			
		} catch(SQLException e) {
			e.printStackTrace();
			throw new SystemException();
		}
		return userPojo;
		
	}

	@Override
	public UserPOJO updateUser(UserPOJO userPojo) throws SystemException{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public UserPOJO getUser(int userID) throws SystemException{
		Connection conn = null;
		try {
			conn = DBUtil.makeConnection();
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM users WHERE user_id = " + userID + ";";
			
			ResultSet resultSet = stmt.executeQuery(query);
			resultSet.next();
			UserPOJO user = new UserPOJO(resultSet.getInt(1), resultSet.getString(2));
			return user;
			
		} catch(SQLException e) {
			e.printStackTrace();
			throw new SystemException();
		}
	}


	@Override
	public void deleteUser(int userID) throws SystemException{
		// TODO Auto-generated method stub

	}

	@Override
	public List<UserPOJO> getJointAccountUsers(int accountID) throws NoJointUserExistsException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//returns user_ID on success, -1 on failure
	public int validateUser(String username, String password) throws SystemException {
		Connection conn = null;
		try {
			conn = DBUtil.makeConnection();
			Statement stmt = conn.createStatement();
			String query = "SELECT user_id FROM users WHERE username = '" + username + "' AND password = crypt('"
					+ password + "', password);";
			ResultSet resultSet = stmt.executeQuery(query);
			
			if(!resultSet.isBeforeFirst()) {
				return -1;
			} else {
				resultSet.next();
				return resultSet.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw new SystemException();
		}
	}
}
