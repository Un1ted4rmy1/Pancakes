package me.Un1ted.Pancakes;

import org.bukkit.plugin.java.JavaPlugin;

public class Pancakes extends JavaPlugin {
	private static Pancakes instance;
	
	public void onEnable() {
		instance = this;
	}
	
	public void onDisable() {
		
	}
	
	public static Pancakes getPlugin() {
		return instance;
	}
}
