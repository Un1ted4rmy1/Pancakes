package rocks.breakfastcraft.Pancakes;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

import lombok.Getter;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import rocks.breakfastcraft.Eggs.EasterEggs;
import rocks.breakfastcraft.Mailbox.Mailbox;
import rocks.breakfastcraft.Pancakes.API.Config;
import rocks.breakfastcraft.Pancakes.API.ConfigManager;
import rocks.breakfastcraft.Pancakes.Events.EventPlugin;
import rocks.breakfastcraft.SQL.SQL;
import rocks.breakfastcraft.SQL.SQLAPI;
import rocks.breakfastcraft.Survival.Survival;

public class Pancakes extends JavaPlugin {
	@Getter
	private static Pancakes instance;
	public static Connection sqlConnection = null;
	@Getter
	private Config databaseConfig;
	@Getter
	private Config serverConfig;
	@Getter
	private String serverType = "None";

	public void onEnable() {
		instance = this;
		databaseConfig = ConfigManager.createConfig(this.getDataFolder(), "database.yml");
		serverConfig = ConfigManager.createConfig(this.getDataFolder(), "server.yml");
		serverType = (String) serverConfig.getValue("Type");
		//Starts the MYSQL Connection - Required
		startConnection();
		new SQL();
		new Mailbox();
		if (serverType == "Adventure") {
			new EventPlugin();
			new EasterEggs();
		} else if (serverType == "Survival") {
			new Survival();
		} else {
			getLogger().log(Level.SEVERE, "Specify server type in Pancakes server.yml!");
			this.setEnabled(false);
			return;
		}
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
}
