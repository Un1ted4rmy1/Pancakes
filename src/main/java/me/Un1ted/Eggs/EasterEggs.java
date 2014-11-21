package me.Un1ted.Eggs;

import me.Un1ted.Pancakes.MiniPlugin;
import me.Un1ted.SQL.SQLCommand;

public class EasterEggs implements MiniPlugin{
	public EasterEggs()
	{
		plugin.getCommand("lovely").setExecutor(new EasterEggCommands());
	}
	

}
