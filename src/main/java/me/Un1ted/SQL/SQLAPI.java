package me.Un1ted.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLAPI {
	/**
	 * Returns the current connection to the MySQL server. If a connection is not set, it'll try to make one.
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
	    Connection conn = null;
	    conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3389", "bhs109-26", "1d551c804c");
	    return conn;
	}
}
