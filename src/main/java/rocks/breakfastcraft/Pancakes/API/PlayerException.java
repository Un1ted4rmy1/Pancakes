package rocks.breakfastcraft.Pancakes.API;

import org.bukkit.entity.Player;

public class PlayerException extends Exception{

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
	public static class InvalidPermissionsException extends Exception
	{
		private static final long serialVersionUID = 1L;
		
		public InvalidPermissionsException(String message, Player player)
		{
			super(message);
			givenPlayer = player;
		}
		public Player getPlayer()
		{
			return givenPlayer;
		}
		
	}
	private static Player givenPlayer = null;
}
