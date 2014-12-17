package rocks.breakfastcraft.Mailbox;

import rocks.breakfastcraft.Pancakes.MiniPlugin;

public class Mailbox implements MiniPlugin{

	public Mailbox()
	{
		new MailboxCommands().register();
	}
}
