package rocks.breakfastcraft.Survival;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import rocks.breakfastcraft.Pancakes.Pancakes;

public class FirstJoin implements Listener {
	Pancakes plugin;
	
	public FirstJoin(Pancakes plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		// Check if player has played before.
		if (!player.hasPlayedBefore()) {
			ItemStack ruleBook = new ItemStack(Material.WRITTEN_BOOK);
			BookMeta ruleBookMeta = (BookMeta) ruleBook.getItemMeta();
			ruleBookMeta.addPage("§");
			ruleBook.setItemMeta(ruleBookMeta);
			player.getInventory().addItem(ruleBook);
		}
	}
}
