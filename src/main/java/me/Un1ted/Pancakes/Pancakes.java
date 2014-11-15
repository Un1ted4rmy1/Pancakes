package me.Un1ted.Pancakes;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

import me.Un1ted.SQL.SQL;
import me.Un1ted.SQL.SQLAPI;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Pancakes extends JavaPlugin {
	private static Pancakes instance;
	public static Connection sqlConnection = null;
	
	public void onEnable() {
		instance = this;
		//Starts the MYSQL Connection - Required
		startConnection();
		new SQL();
	}
	
	public void onDisable() {
		
	}
	
	public void startConnection()
	{
		try 
		{
			sqlConnection = SQLAPI.getConnection();
		}
		catch (SQLException e)
		{
			getLogger().log(Level.SEVERE, "Unable to find database. Disabling plugin.");
			Bukkit.broadcastMessage("Unable to connect to the database. Core Management plugin disabled.");
			this.setEnabled(false);
		}
	}
	public static Pancakes getPlugin() {
		return instance;
	}
}
