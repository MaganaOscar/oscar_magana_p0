package model;

public class UserPOJO {
	private int userID;
	private String username;
	private String password;
	
	public UserPOJO() {
		super();
	}
	
	public UserPOJO(int userID, String username) {
		super();
		this.userID = userID;
		this.username = username;
	}
	
	public UserPOJO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public UserPOJO(int userID, String username, String password) {
		super();
		this.userID = userID;
		this.username = username;
		this.password = password;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserPOJO [userID=" + userID + ", username=" + username + ", password=" + password + "]";
	}
	
	
	
}
