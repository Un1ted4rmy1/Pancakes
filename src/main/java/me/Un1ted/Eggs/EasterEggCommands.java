package me.Un1ted.Eggs;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EasterEggCommands implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player)
		{
			Player commander = (Player) sender;
			
			if (label.equalsIgnoreCase("lovely"))
			{
				commander.sendMessage("Rawr.");
			}
		}
		return false;
	}
}
