package rocks.breakfastcraft.Survival;

import java.util.logging.Level;

import rocks.breakfastcraft.Pancakes.MiniPlugin;

public class Survival implements MiniPlugin {
	public Survival() {
		plugin.getServer().getPluginManager().registerEvents(new FirstJoin(plugin), plugin);
		if (plugin.getServer().getPluginManager().getPlugin("mcMMO") != null) {
			plugin.getServer().getPluginManager().registerEvents(new McMMORankup(plugin), plugin);
		} else {
			plugin.getLogger().log(Level.WARNING, "Cannot find mcMMO! Disabling mcMMO features!");
		}
	}
}
