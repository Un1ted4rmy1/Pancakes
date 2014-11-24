package me.Un1ted.Pancakes.API;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommonMethods {
	public static String CombineArgsToString(String[] arguments, int startAt)
	{
		
		String value = "";
		for (int i = startAt; i < arguments.length; i++)
		{
			if (i == (arguments.length - 1))
			{
				value += " " + arguments[i];
			}
			else
			{
				value += " " + arguments[i] + " ";
			}
		}
		
		return value;
	}
	public static boolean isPlayerOnline(Player player)
	{
		for (Player online : Bukkit.getOnlinePlayers())
		{
			if (online == player)
				return true;
		}
		return false;
	}
	public static boolean isPlayerOnline(String name)
	{
		for (Player online : Bukkit.getOnlinePlayers())
		{
			if (online.getName().equalsIgnoreCase(name))
				return true;
		}
		return false;
	}
}
