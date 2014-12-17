package rocks.breakfastcraft.Pancakes;

import java.sql.Connection;
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
		setupConfigs();
		serverType = (String) serverConfig.getValue("Type");
		//Starts the MYSQL Connection - Required
		startConnection();
		new SQL();
		new Mailbox();
		if (serverType.equals("Adventure")) {
			new EventPlugin();
			new EasterEggs();
		} else if (serverType.equals("Survival")) {
			new Survival();
		} else {
			getLogger().log(Level.SEVERE, "Specify server type in Pancakes server.yml!");
			this.setEnabled(false);
			return;
		}
	}

	public void onDisable() {
		ConfigManager.onDisable();
	}

	public void startConnection()
	{
		try 
		{
			sqlConnection = SQLAPI.getConnection();
		}
		catch (Exception e)
		{
			getLogger().log(Level.SEVERE, "Unable to find database. Disabling plugin.");
			Bukkit.broadcastMessage("Unable to connect to the database. Core Management plugin disabled.");
			this.setEnabled(false);
		}
	}

	public void setupConfigs() {
		databaseConfig = ConfigManager.createConfig(this.getDataFolder(), "database.yml");
		serverConfig = ConfigManager.createConfig(this.getDataFolder(), "server.yml");
		if (!serverConfig.getYaml().contains("Type")) {
			serverConfig.setValue("Type", "None");
		}
		if (!databaseConfig.getYaml().contains("Username")) {
			databaseConfig.setValue("Username", "bukkit");
		}
		if (!databaseConfig.getYaml().contains("Password")) {
			databaseConfig.setValue("Password", "password");
		}
		if (!databaseConfig.getYaml().contains("Default.Hostname")) {
			databaseConfig.setValue("Default.Hostname", "localhost");
		}
		if (!databaseConfig.getYaml().contains("Default.Port")) {
			databaseConfig.setValue("Default.Port", 3306);
		}
		if (!databaseConfig.getYaml().contains("Default.Database")) {
			databaseConfig.setValue("Default.Database", "Pancakes");
		}
	}
}
