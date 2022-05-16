package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	static Connection conn;
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	static Connection makeConnection() throws SQLException {
		String connectionURL = "jdbc:postgresql://oscar-magana-p0.cbh24wt1kcex.us-west-1.rds.amazonaws.com:5432/oscar_magana_p0";
		String username = "postgres";
		String password = "rootroot";
		if(conn == null) {
			conn = DriverManager.getConnection(connectionURL, username, password);
		}
		return conn;
	}
}
