package me.Un1ted.Pancakes.API;

import org.bukkit.entity.Player;

public class PlayerException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public PlayerException(String message)
	{
		super(message);
	}
	public PlayerException(String message, Player player)
	{
		super(message);
		givenPlayer = player;
	}
	public Player getPlayer()
	{
		return givenPlayer;
	}
	private Player givenPlayer = null;
}
