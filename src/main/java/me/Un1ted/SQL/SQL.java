package me.Un1ted.SQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;

import org.bukkit.Bukkit;

import me.Un1ted.Pancakes.MiniPlugin;



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
			Bukkit.getLogger().log(Level.SEVERE, "[Pancakes] {EXCEPTION} Unable to generate SELECT query successfully.");
			return null;
		}
	}
}
