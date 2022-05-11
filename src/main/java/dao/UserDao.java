package dao;

import java.util.List;

import exception.NoJointUserExistsException;
import model.UserPOJO;

public interface UserDao {
	
	UserPOJO addUser(UserPOJO userPojo);
	
	UserPOJO updateUser(UserPOJO userPojo);
	
	void deleteUser(int userID);
	
	List<UserPOJO> getJointAccountUsers (int accountID) throws NoJointUserExistsException;	
	
	UserPOJO getUser(int userID);
}
