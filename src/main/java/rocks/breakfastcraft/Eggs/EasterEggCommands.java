package rocks.breakfastcraft.Eggs;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rocks.breakfastcraft.Pancakes.API.AbstractCommand;

public class EasterEggCommands extends AbstractCommand{

	public EasterEggCommands() {
		super("lovely"/*, "usage", "descriptions", "permissionMessage", Arrays.asList("additional", "additional")*/);
	}

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
