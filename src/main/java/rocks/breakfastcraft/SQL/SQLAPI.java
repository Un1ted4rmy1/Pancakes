package rocks.breakfastcraft.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLAPI {
	/**
	 * Returns the current connection to the MySQL server. If a connection is not set, it'll try to make one.
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
	    Connection conn = null;
	    conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bhs109-26", "bhs109-26", "1d551c804c");
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
