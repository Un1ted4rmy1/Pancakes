package me.Un1ted.Pancakes.Punishments;

import me.Un1ted.Pancakes.MiniPlugin;

public class Punishments implements MiniPlugin {
	public Punishments() {
		plugin.getCommand("punish").setExecutor(new PunishCommand());
	}
}
