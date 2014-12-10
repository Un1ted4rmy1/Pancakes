package rocks.breakfastcraft.Pancakes.Punishments;

import rocks.breakfastcraft.Pancakes.MiniPlugin;

public class Punishments implements MiniPlugin {
	public Punishments() {
		plugin.getCommand("punish").setExecutor(new PunishCommand());
	}
}
