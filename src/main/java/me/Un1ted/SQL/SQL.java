package me.Un1ted.SQL;

import me.Un1ted.Pancakes.MiniPlugin;



public class SQL implements MiniPlugin{
	public SQL(){
		plugin.getCommand("db").setExecutor(new SQLCommand());
	}
}
