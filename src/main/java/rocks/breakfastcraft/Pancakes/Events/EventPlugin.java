package rocks.breakfastcraft.Pancakes.Events;

import rocks.breakfastcraft.Pancakes.MiniPlugin;

public class EventPlugin implements MiniPlugin {
	public EventPlugin()
	{
		plugin.getServer().getPluginManager().registerEvents(new PlayerEntranceEvents(), plugin);
	}
}
