package rocks.breakfastcraft.Eggs;

import rocks.breakfastcraft.Pancakes.MiniPlugin;

public class EasterEggs implements MiniPlugin{
	public EasterEggs()
	{
		plugin.getCommand("lovely").setExecutor(new EasterEggCommands());
	}
	

}
