package net.punchtree.core.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;

public class OnJoinListener implements Listener {

//	private static final Location MINIGAMES_SPAWN = new Location(Bukkit.getWorld("Quarantine"), )

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

//		PunchTreePlayer ptp = getPunchtreePlayer(player);

		event.joinMessage(Component.text(ChatColor.RED + player.getName() + " is playing minigames?!"));
//		player.teleport(MINIGAMES_SPAWN);
//		displayScoreboard(player);
	}

}
