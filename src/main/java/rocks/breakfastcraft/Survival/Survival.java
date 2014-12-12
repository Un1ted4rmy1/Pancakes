package rocks.breakfastcraft.Survival;

import rocks.breakfastcraft.Pancakes.MiniPlugin;

public class Survival implements MiniPlugin {
	public Survival() {
		plugin.getServer().getPluginManager().registerEvents(new FirstJoin(plugin), plugin);
	}
}
