package me.Un1ted.SQL;

import java.sql.SQLException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SQLCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		//Determine if the command sender is a player in game or console.
		if (sender instanceof Player)
		{
			Player commander = (Player) sender;

			/*
			 * [/db]
			 * User has ran the default "help" command.
			 */
			if (label.equalsIgnoreCase("db"))
			{
				/*
				 * [/db]
				 * Default command. Shows the "help"
				 */
				if (args.length == 0)
				{
					commander.sendMessage("SQL Commands: ");
					commander.sendMessage("/db connection - Tests the current connection.");
					commander.sendMessage("/db connection close - Force close the current connection.");
					
				}
				else if (args.length == 1)
				{
					/*
					 * [/db connection]
					 * User has decided to test the connection of the MySQL Database 
					 */
					if (args[1].equalsIgnoreCase("connection"))
					{
						//This method requires a try-catch because it may throw an exception.
						try 
						{
							//Verify that the connection is valid.
							if (SQLAPI.getConnection() != null)
							{
								//It worked!
								commander.sendMessage("Connected to SQL Database...");
							}
							else
							{
								//Manually deter user to get exception.
								throw new SQLException("Database connection failed.");
							}
						} 
						catch (SQLException e)
						{
							commander.sendMessage("SQL Connection Failed! Reason:");
							commander.sendMessage(e.getMessage());
						}
						
					}
				}
				else if (args.length == 2)
				{
					
					if (args[0].equalsIgnoreCase("connection"))
					{
						if (args[1].equalsIgnoreCase("close"))
						{
							try 
							{
								//Verify that the connection is valid.
								if (SQLAPI.getConnection() != null)
								{
									//It worked!
									SQLAPI.getConnection().close();
									commander.sendMessage("SQL Connection severed.");
								}
								else
								{
									//Manually deter user to get exception.
									throw new SQLException("Database connection failed.");
								}
							} 
							catch (SQLException e)
							{
								commander.sendMessage("SQL Connection Failed! Reason:");
								commander.sendMessage(e.getMessage());
							}
						}
					}
				}
				
			}
		
			/*
			 * [/db <param>]
			 * User has ran the database command with a single parameter.
			 */
			if (args[0].equalsIgnoreCase("db"))
			{
				
					
				

				
			}
			
		}
		else
		{
			
		}
		return false;
	}
}
