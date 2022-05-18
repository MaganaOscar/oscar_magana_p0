package dao;

import java.util.List;

import exception.NoJointUserExistsException;
import exception.SystemException;
import model.UserPOJO;

public interface UserDao {
	
	UserPOJO addUser(UserPOJO userPojo) throws SystemException;
	
	UserPOJO updateUser(UserPOJO userPojo) throws SystemException;
	
	UserPOJO getUser(int userID) throws SystemException;
	
	void deleteUser(int userID) throws SystemException;
	
	List<UserPOJO> getJointAccountUsers (int accountID) throws NoJointUserExistsException, SystemException;	
	
	int validateUser(String username, String password) throws SystemException;
}
