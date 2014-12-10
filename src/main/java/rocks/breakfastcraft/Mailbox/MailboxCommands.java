package rocks.breakfastcraft.Mailbox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.IllegalFormatException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import rocks.breakfastcraft.Pancakes.Globals;
import rocks.breakfastcraft.Pancakes.Permissions;
import rocks.breakfastcraft.Pancakes.API.CommonMethods;
import rocks.breakfastcraft.Pancakes.API.TimeAPI;
import rocks.breakfastcraft.SQL.SQL;
import rocks.breakfastcraft.SQL.SQLAPI;
import rocks.breakfastcraft.SQL.SQLRef;


public class MailboxCommands implements CommandExecutor{
	/**
	 * When the user performs a command that is relevant to our Mailbox system.
	 */
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player)
		{
			Player commander = (Player) sender;
			if (commander.hasPermission(Permissions.mailbox_default))
			{
				
				if (label.equalsIgnoreCase("mailbox"))
				{
					//Command: [/mailbox]
					if (args.length == 0)
					{
						//Show message numbers...
						//SQLRef.getPlayerID(commander)
						commander.sendMessage("Ran command.");
						ResultSet messages = SQL.Select("SELECT * FROM Mailbox_Content WHERE MailboxID='" + SQLRef.getPlayerMailboxID(commander) + "';");
						try
						{
							ItemStack chatBook = new ItemStack(Material.WRITTEN_BOOK);
							BookMeta book = (BookMeta) chatBook.getItemMeta();
							book.setAuthor(commander.getName());
							book.setTitle("Recent Messages");
							while (messages.next())
							{
								book.addPage("Sent: \n" + messages.getString("Sent")
										+ "Message: \n"
										+ messages.getString("Message"));
								
							}
							chatBook.setItemMeta(book);
							commander.getInventory().addItem(chatBook);
						}
						catch (Exception ex)
						{
							Bukkit.getLogger().log(Level.INFO, ex.toString());
						}
					}
					//Command: [/mailbox args0]
					if (args.length == 1)
					{
						//No commands I can think of D:
					}
					//Command: [/mailbox args0, args1]
					if (args.length == 2)
					{
						//Command: [/mailbox delete <number>]
						if (args[0].equalsIgnoreCase("delete"))
						{
							//Verify they have the specific permission
							if (commander.hasPermission(Permissions.mailbox_delete_message))
							{
								try
								{
									int messageNum = Integer.parseInt(args[1]);
									ResultSet messages = SQL.runQuery(
											"SELECT * " +
											"FROM Mailbox_Content" +
											"WHERE PlayerID='" + SQLRef.getPlayerID(commander) + "'");
									int counter = 1;
									try {
										while (messages.next())
										{
											if (messageNum == counter)
											{
												SQL.Update(
														"UPDATE Mailbox_Content" +
														"SET Visible='false'" +
														"WHERE PlayerID='" + SQLRef.getPlayerID(commander) + "' AND ContentID='" + messages.getInt("ContentID") + "';");
											}
										}
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								catch (IllegalFormatException ex)
								{
									commander.sendMessage("Please enter in a valid number.");
								}
								
							}
							else
							{
								commander.sendMessage(Globals.logo_default + "Please use [/mailbox clear <number>] (Note: <number> is optional!)");
							}
							
						}
						if (args[0].equalsIgnoreCase("clear"))
						{
							
						}
					}
					//Command: [/mailbox args0, args1, args2, ...]
					if (args.length > 2)
					{
						//Command: [/mailbox send <player> <message>]
						if (args[0].equalsIgnoreCase("send"))
						{
							
							if (CommonMethods.isPlayerOnline(args[1]))
							{
								/*
								 * If the player is on the server, they're in that database 100%.
								 */
								
								Player to = (Player) Bukkit.getPlayer(args[1]);
								Player from = (Player) commander;
								int toID = SQLRef.getPlayerID(to);
								int fromID = SQLRef.getPlayerID(from);
								int mailboxID = SQLRef.getPlayerMailboxID(to);
								
								commander.sendMessage("To ID: " + toID);
								commander.sendMessage("From ID: " + fromID);
								commander.sendMessage("Mailbox ID: " + mailboxID);
								commander.sendMessage("Got this far...");
								//Send message to user.
								commander.sendMessage("Ran? " 
										+ SQL.Insert("INSERT INTO "
										+ "Mailbox_Content "
										+ ""
										+ "VALUES ('',"
										+ "'" + Integer.toString(mailboxID) + "', "
										+ "'" + Integer.toString(toID) + "', "
										+ "'" + Integer.toString(fromID) + "', "
										+ "'" + CommonMethods.CombineArgsToString(args, 2) + "', "
										+ "'" + TimeAPI.getCurrentTimeStamp() + "', '0', '1')"));
								
							}
							else
							{
								/*
								 * Manually check the database if we have that player on record
								 * If not, we shouldn't try to add a message to a player that doesn't exist!
								 */
								ResultSet players = SQL.Select("SELECT * FROM Players WHERE Name='" + args[1] + "'");
								int numFound = SQLAPI.getNumRows(players);
								if (numFound == 1)
								{
									//Found them! Send them the mail
									String to = args[1];
									Player from = (Player) commander;
									int toID = SQLRef.getPlayerID(to);
									int fromID = SQLRef.getPlayerID(from);
									int mailboxID = SQLRef.getPlayerMailboxID(to);
									//Send message to user.
									SQL.Insert("INSERT INTO "
											+ "Mailbox_Content "
											+ "(MailboxID, To, From, Message, Sent)"
											+ "VALUES ("
											+ "'" + mailboxID + "', "
											+ "'" + toID + "', "
											+ "'" + fromID + "', "
											+ "'" + CommonMethods.CombineArgsToString(args, 2) + "'"
											+ "'" + TimeAPI.getCurrentTimeStamp() + "');");
									
								}
								else
								{
									commander.sendMessage(Globals.logo_default + " The player you tried to send mail to is not an existing player!");
								}
							}
							
							
						}
					}
				}
			
			}
			else
			{
				commander.sendMessage(Globals.logo_default + " You do not have permission to perform this command.");
			}
			
		}
		return false;
	}
}
