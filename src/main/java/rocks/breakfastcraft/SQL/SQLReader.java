package rocks.breakfastcraft.SQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;

import org.bukkit.Bukkit;

public class SQLReader {
	
	public static ResultSet SELECT(String Table, String[] Parameters, String[] Conditions)
	{
		try
		{
			Connection conn = SQLAPI.getConnection();
			Statement statement = conn.createStatement();
			String query = "SELECT ";
			//Generate SELECT statement
			if (Parameters[0].equalsIgnoreCase("*"))
			{
				query = "SELECT * FROM ";
			}
			else
			{
				
				for (int i = 0; i < Parameters.length; i++)
				{
					if (i == (Parameters.length - 1))
					{
						query += Parameters[i] + " FROM ";
					}
					else
						query += Parameters[i] + ", ";
					
				}
			}
			//Add Table
			query += "`"+ Table + "` WHERE ";
			
			//Add Conditions
			if (Conditions != null)
			{
				for (int j = 0; j < Conditions.length; j++)
				{
					if (j == (Parameters.length - 1))
					{
						query += Conditions[j] + ";";
					}
					else
						query += Conditions[j] + " ";
				}	
			}
			else
				query += ";";
			
			
			return statement.executeQuery(query);
		}
		catch (Exception ex)
		{
			Bukkit.getLogger().log(Level.SEVERE, "[Pancakes] {EXCEPTION} Unable to generate SELECT query successfully.");
			return null;
		}
	}
	 
}
