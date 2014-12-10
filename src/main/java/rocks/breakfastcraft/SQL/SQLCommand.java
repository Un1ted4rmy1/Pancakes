package rocks.breakfastcraft.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

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
					if (args[0].equalsIgnoreCase("test"))
					{
						
						try {
							ResultSet SelectHelloWorld = SQL.runQuery("SELECT * FROM HelloWorld");
							int counter = 0;
							commander.sendMessage("Beginning Query...");
							while (SelectHelloWorld.next())
							{
								counter++;
								
								
								commander.sendMessage("------------------------");
								commander.sendMessage("Player ID: " + SelectHelloWorld.getInt("PlayerID"));
								commander.sendMessage("Player Name: " + SelectHelloWorld.getString("Name"));
								commander.sendMessage("------------------------");
							}
							commander.sendMessage("Results Found: " + counter);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							Bukkit.broadcastMessage("Unable to create query...");
						}
					}
					/*
					 * [/db connection]
					 * User has decided to test the connection of the MySQL Database 
					 */
					if (args[0].equalsIgnoreCase("connection"))
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
					if (args[0].equalsIgnoreCase("players"))
					{
						ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
						BookMeta bookProps = (BookMeta) book.getItemMeta();
						bookProps.setAuthor("BreakfastCraft DATA");
						bookProps.setTitle("All Players");
						try {
							ResultSet set = SQL.runQuery("SELECT * FROM Players");
							while(set.next())
							{
								bookProps.addPage(
										"Username: \n§7§o" + set.getString("Name") + "\n" +
										"§rJoin Date: \n§7§o" + set.getString("Date")
										);
							}
							book.setItemMeta(bookProps);
							commander.getInventory().setItemInHand(book);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							commander.sendMessage("[PancakeData] UNABLE TO FINISH. ALERT RYAN!!!");
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
					if (args[0].equalsIgnoreCase("num"))
					{
						if (args[1].equalsIgnoreCase("players"))
						{
							int counter = 0;
							commander.sendMessage("[PancakeData] Running through all players to ever register...");
							try {
								ResultSet set = SQL.runQuery("SELECT * FROM Players");
								while(set.next())
								{
									counter++;
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								commander.sendMessage("[PancakeData] UNABLE TO FINISH. ALERT RYAN!!!");
							}
							commander.sendMessage("[PancakeData] Number of users that joined: " + counter);
						}
					}
					
				}
				
			}
	
			
		}
		else
		{
			
		}
		return false;
	}
}
