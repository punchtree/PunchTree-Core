package net.punchtree.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.cxom.melee2.Melee;
import net.punchtree.core.command.PlayCommand;
import net.punchtree.core.listeners.PressurePlateListener;
import net.punchtree.core.logging.MessageOfTheDay;
import net.punchtree.minigames.game.PvpGame;
import net.punchtree.minigames.lobby.Matchmaker;

public class PunchTreeCorePlugin extends JavaPlugin {

	private Matchmaker meleeMatchmaker;
	private Matchmaker rabbitMatchmaker;
	private Location MINIGAMES_HUB;
	
	public static PunchTreeCorePlugin getInstance() {
		return getPlugin(PunchTreeCorePlugin.class);
	}

	@Override
	public void onEnable(){
		saveDefaultConfig();

		MINIGAMES_HUB = new Location(Bukkit.getWorld("Quarantine"), 4000.5, 71, -2499.5);
		Consumer<Player> onPlayerLeaveLobby = this::spawnPlayerAtHub;
		
		List<PvpGame> possibleMeleeMatches = new ArrayList<>(Melee.getPlugin().getMeleeGameManager().getGamesList());
		meleeMatchmaker = new Matchmaker(possibleMeleeMatches, onPlayerLeaveLobby);
		
		List<PvpGame> possibleRabbitMatches = new ArrayList<>(Melee.getPlugin().getRabbitGameManager().getGamesList());
		rabbitMatchmaker = new Matchmaker(possibleRabbitMatches, onPlayerLeaveLobby);
		
		getCommand("play").setExecutor(new PlayCommand());
		
		registerEvents();
	}
	
	private void spawnPlayerAtHub(Player player) {
		player.teleport(MINIGAMES_HUB);
		player.setGameMode(GameMode.ADVENTURE);
		player.setInvulnerable(true);
		player.clearActiveItem();
		player.getInventory().clear();
	}
	
	private void registerEvents() {
		Bukkit.getServer().getPluginManager().registerEvents(new MessageOfTheDay(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PressurePlateListener(), this);
	}

	public Matchmaker getMatchmaker(String gameName) {
		switch(gameName) {
		case "melee":
			return meleeMatchmaker;
		case "rabbit":
			return rabbitMatchmaker;
		default:
			return null;
		}
	}
	
}
