package me.Un1ted.Mailbox;

import me.Un1ted.Pancakes.MiniPlugin;

public class Mailbox implements MiniPlugin{

	public Mailbox()
	{
		plugin.getCommand("mailbox").setExecutor(new MailboxCommands());
	}
}
