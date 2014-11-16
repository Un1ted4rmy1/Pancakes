package me.Un1ted.Pancakes.Events;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import me.Un1ted.Pancakes.API.TimeAPI;
import me.Un1ted.SQL.SQL;
import me.Un1ted.SQL.SQLAPI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerEntranceEvents implements Listener{

	@EventHandler
	public static void DatabaseCheck(PlayerJoinEvent event)
	{
		Player joiner = (Player) event.getPlayer();
		ResultSet allPlayers = SQL.runQuery("SELECT * FROM Players WHERE Name='" + joiner.getName() + "' OR UUID='" + joiner.getUniqueId() + "';");
		int counter = 0;
		try {
			while (allPlayers.next())
			{
				counter++;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		joiner.sendMessage("counter: " + counter);
		if (counter == 0)	
		{
			try
			{
				joiner.sendMessage("[BreakfastCraft] At least it's running the query...");
				Connection conn = SQLAPI.getConnection();
				Statement statement = conn.createStatement();
				statement.executeUpdate("INSERT INTO Players (UUID, IP, Name, Date) VALUES"
						+ " ('" + joiner.getUniqueId().toString() + "', '" + joiner.getAddress().getAddress().toString() +"', '" + joiner.getName().toString() + "', '"
								+ TimeAPI.getCurrentTimeStamp() + "')");
			}
			catch (Exception ex)
			{
				Bukkit.getLogger().log(Level.SEVERE, "[Pancakes] {EXCEPTION} Ryan...." + ex.getMessage());
				
			}
			joiner.sendMessage("[BreakfastCraft] You are now eligible to register on our website!");
			
		}
		else
		{
			
		}
	}
	
}
