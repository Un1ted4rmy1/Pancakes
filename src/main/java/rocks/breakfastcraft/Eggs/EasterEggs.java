package rocks.breakfastcraft.Eggs;

import rocks.breakfastcraft.Pancakes.MiniPlugin;

public class EasterEggs implements MiniPlugin{
	public EasterEggs()
	{
		new EasterEggCommands().register();
	}
	

}
