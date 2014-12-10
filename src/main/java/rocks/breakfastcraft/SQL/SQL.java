package rocks.breakfastcraft.SQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;

import org.bukkit.Bukkit;

import rocks.breakfastcraft.Pancakes.Globals;
import rocks.breakfastcraft.Pancakes.MiniPlugin;



public class SQL implements MiniPlugin{
	public SQL(){
		plugin.getCommand("db").setExecutor(new SQLCommand());
		
	}
	public static ResultSet runQuery(String query)
	{
		try
		{
			Connection conn = SQLAPI.getConnection();
			Statement statement = conn.createStatement();	
			return statement.executeQuery(query);
		}
		catch (Exception ex)
		{
			Bukkit.getLogger().log(Level.SEVERE, Globals.logo_default + " {EXCEPTION} Unable to generate SELECT query successfully.");
			return null;
		}
	}
	public static ResultSet Select(String query)
	{
		try
		{
			Connection conn = SQLAPI.getConnection();
			Statement statement = conn.createStatement();	
			return statement.executeQuery(query);
		}
		catch (Exception ex)
		{
			Bukkit.getLogger().log(Level.SEVERE, Globals.logo_default + " {EXCEPTION} Unable to generate SELECT query successfully.");
			Bukkit.getLogger().log(Level.SEVERE, ex.toString());
			return null;
		}
	}
	public static boolean Insert(String query)
	{
		try
		{
			Connection conn = SQLAPI.getConnection();
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);
			return true;
		}
		catch (Exception ex)
		{
			return false;
		}
	}
	public static boolean Update(String query)
	{
		try
		{
			Connection conn = SQLAPI.getConnection();
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);
			return true;
		}
		catch (Exception ex)
		{
			return false;
		}
	}
}
