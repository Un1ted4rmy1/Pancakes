package me.Un1ted.Eggs;

import me.Un1ted.Pancakes.MiniPlugin;

public class EasterEggs implements MiniPlugin{
	public EasterEggs()
	{
		plugin.getCommand("lovely").setExecutor(new EasterEggCommands());
	}
	

}
