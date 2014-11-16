package me.Un1ted.Pancakes.Events;

import me.Un1ted.Pancakes.MiniPlugin;

public class EventPlugin implements MiniPlugin {
	public EventPlugin()
	{
		plugin.getServer().getPluginManager().registerEvents(new PlayerEntranceEvents(), plugin);
	}
}
