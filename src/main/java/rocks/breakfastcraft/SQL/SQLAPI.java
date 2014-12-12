package rocks.breakfastcraft.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import rocks.breakfastcraft.Pancakes.Pancakes;

public class SQLAPI {
	private static Pancakes plugin = Pancakes.getInstance();
	private static String username = (String) plugin.getDatabaseConfig().getValue("Username");
	private static String password = (String) plugin.getDatabaseConfig().getValue("Password");
	private static String hostname = (String) plugin.getDatabaseConfig().getValue("Default.Hostname");
	private static int port = (int) plugin.getDatabaseConfig().getValue("Default.Port");
	private static String database = (String) plugin.getDatabaseConfig().getValue("Default.Database");
	/**
	 * Returns the current connection to the MySQL server. If a connection is not set, it'll try to make one.
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
	    Connection conn = null;
	    conn = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + port + "/" + database, username, password);
	    return conn;
	}
	public static int getNumRows(ResultSet results)
	{
		try
		{
			int counter = 1;
			while (results.next())
			{
				counter++;
			}
			
			return counter;
		}
		catch (Exception ex)
		{
			return -1;
		}
	}
	public static int getExactRows(ResultSet results)
	{
		try
		{
			int counter = 0;
			while (results.next())
			{
				counter++;
			}
			
			return counter;
		}
		catch (Exception ex)
		{
			return -1;
		}
	}
}
