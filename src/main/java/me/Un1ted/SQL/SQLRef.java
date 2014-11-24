package me.Un1ted.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SQLRef {
	/**
	 * Grabs a Player's specific ID in our database
	 * @param player
	 * @return
	 */
	public static int getPlayerID(Player player)
	{
		ResultSet players = SQL.runQuery("SELECT * FROM Players WHERE UUID='" + player.getUniqueId().toString() + "';");
		try {
			players.first();
			return players.getInt("PlayerID");
		} catch (SQLException ex) {
			Bukkit.getLogger().log(Level.SEVERE, ex.toString());
			return -1;
		}
	}
	/**
	 * Grab's a Player's specific ID in our database
	 * <br />Please only use this method if user is not online...
	 * @param name
	 * @return
	 */
	public static int getPlayerID(String name)
	{
		ResultSet players = SQL.runQuery("SELECT * FROM Players WHERE Name='" + name + "';");
		try {
			return players.getInt("PlayerID");
		} catch (SQLException ex) {
			Bukkit.getLogger().log(Level.SEVERE, ex.toString());
			return -1;
		}
	}
	public static int getPlayerMailboxID(Player player)
	{
		ResultSet players = SQL.runQuery("SELECT * FROM Mailbox WHERE PlayerID='" + getPlayerID(player) + "';");
		try {
			players.first();
			return players.getInt("MailboxID");
		} catch (SQLException ex) {
			Bukkit.getLogger().log(Level.SEVERE, ex.toString());
			return -1;
		}
	}
	public static int getPlayerMailboxID(String name)
	{
		ResultSet players = SQL.runQuery("SELECT * FROM Mailbox WHERE PlayerID='" + getPlayerID(name) + "';");
		try {
			players.first();
			return players.getInt("MailboxID");
		} catch (SQLException ex) {
			Bukkit.getLogger().log(Level.SEVERE, ex.toString());
			return -1;
		}
	}
	public static String ConverPlayerIDToName(int ID)
	{
		ResultSet players = SQL.runQuery("SELECT * FROM Players WHERE PlayerID='" + ID + "'");
		try
		{
			players.first();
			return players.getString("Name");
		}
		catch (Exception ex)
		{
			Bukkit.getLogger().log(Level.SEVERE, ex.toString());
			return "";
		}
		
	}
	
	
}
