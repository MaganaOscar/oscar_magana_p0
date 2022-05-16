package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import exception.NoJointUserExistsException;
import model.UserPOJO;

public class UserDaoDatabaseImpl implements UserDao {

	@Override
	public UserPOJO addUser(UserPOJO userPojo) {
		Connection conn = null;
		try {
			conn = DBUtil.makeConnection();
			Statement stmt = conn.createStatement();
			String query = "INSERT INTO users(username, password) VALUES ('" + userPojo.getUsername() + "', '" 
					+ userPojo.getPassword() + "') returning user_id;";
			
			ResultSet resultSet = stmt.executeQuery(query);
			resultSet.next();
			userPojo.setUserID(resultSet.getInt(1));
			
		} catch(SQLException e) {
			e.printStackTrace();
			//throw new exception
		}
		return userPojo;
		
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

}
