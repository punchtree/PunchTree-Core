package net.punchtree.core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;

public class OnJoinListener implements Listener {

	private static final String BYPASS_SPAWN_ON_JOIN_PERMISSION = "bypass.spawn_on_join";
	private static Location MINIGAMES_SPAWN;

	public OnJoinListener() {
		MINIGAMES_SPAWN = new Location(Bukkit.getWorld("Quarantine"), 4000.5, 71, -2499.5);
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

//		PunchTreePlayer ptp = getPunchtreePlayer(player);

		event.joinMessage(Component.text(ChatColor.RED + player.getName() + " is playing minigames?!"));

		if (!canBypassSpawnOnJoin(player)) {
			player.teleport(MINIGAMES_SPAWN);
		}
//		player.teleport(MINIGAMES_SPAWN);
//		displayScoreboard(player);
	}

	private boolean canBypassSpawnOnJoin(Player player) {
		return player.hasPermission(BYPASS_SPAWN_ON_JOIN_PERMISSION);
	}

}
