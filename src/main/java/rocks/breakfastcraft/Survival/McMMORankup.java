package rocks.breakfastcraft.Survival;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import rocks.breakfastcraft.Pancakes.Pancakes;

import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.datatypes.skills.SkillType;
import com.gmail.nossr50.events.experience.McMMOPlayerLevelUpEvent;

public class McMMORankup implements Listener {
	private Pancakes plugin;
	
	public McMMORankup(Pancakes plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onMcMMOPlayerLevelUp(McMMOPlayerLevelUpEvent event) {
		Player player = event.getPlayer();
		SkillType skill = event.getSkill();
		int levels = event.getLevelsGained();
		ExperienceAPI.getLevel(player, "Mining");
	}
}
