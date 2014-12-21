package rocks.breakfastcraft.Pancakes.Events;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import rocks.breakfastcraft.Pancakes.API.TimeAPI;
import rocks.breakfastcraft.SQL.SQL;
import rocks.breakfastcraft.SQL.SQLAPI;
import rocks.breakfastcraft.SQL.SQLRef;

public class PlayerEntranceEvents implements Listener {
	@EventHandler
	public static void DatabaseCheck(PlayerJoinEvent event)
	{
		Player joiner = event.getPlayer();
		ResultSet allPlayers = SQL.runQuery("SELECT * FROM Players WHERE Name='" + joiner.getName() + "' OR UUID='" + joiner.getUniqueId() + "';");
		int counter = 0;
		
		try {
			while (allPlayers.next())
			{
				counter++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (counter == 0)	
		{
			try
			{
				Connection conn = SQLAPI.getConnection();
				Statement statement = conn.createStatement();
				statement.executeUpdate("INSERT INTO Players (UUID, IP, Name, Date) VALUES"
						+ " ('" + joiner.getUniqueId().toString() + "', '" + joiner.getAddress().getAddress().toString() +"', '" + joiner.getName().toString() + "', '"
								+ TimeAPI.getCurrentTimeStamp() + "')");
			}
			catch (Exception ex)
			{
				Bukkit.getLogger().log(Level.SEVERE, "[Pancakes] {EXCEPTION} Method: DatabaseCheck" + ex.getMessage());
			}
			joiner.sendMessage("[BreakfastCraft] You are now eligible to register on our website!");
			
		}
		else
		{
			
		}
	}
	
	@EventHandler
	public static void CreateMailbox(PlayerJoinEvent event)
	{
		try
		{
			Player joiner = event.getPlayer();
			
			//Determine if they are in our database
			ResultSet mailboxes = SQL.Select("SELECT * FROM Mailbox WHERE PlayerID='" + SQLRef.getPlayerID(joiner) + "';");
			if (SQLAPI.getExactRows(mailboxes) == 0)
			{
				SQL.Insert("INSERT INTO Mailbox (PlayerID) VALUES ('" + SQLRef.getPlayerID(joiner) + "');");
			}
		}
		catch (Exception ex)
		{
			Bukkit.getLogger().log(Level.SEVERE, "[Pancakes] {EXCEPTION} Method: CreateMailbox" + ex.getMessage());
		}
	}
	
}
