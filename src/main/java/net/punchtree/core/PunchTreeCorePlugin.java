package net.punchtree.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import net.punchtree.melee.Melee;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.cxom.jailbreak3.Jailbreak;
import net.punchtree.core.command.PlayCommand;
import net.punchtree.core.listeners.PressurePlateListener;
import net.punchtree.core.logging.MessageOfTheDay;
import net.punchtree.minigames.game.PvpGame;
import net.punchtree.minigames.lobby.Matchmaker;

public class PunchTreeCorePlugin extends JavaPlugin {

	private Matchmaker meleeMatchmaker;
	private Matchmaker rabbitMatchmaker;
	private Matchmaker jailbreakMatchmaker;
//	private Matchmaker battleMatchmaker;
	private Location MINIGAMES_HUB;
	
	public static PunchTreeCorePlugin getInstance() {
		return getPlugin(PunchTreeCorePlugin.class);
	}

	@Override
	public void onEnable(){
		saveDefaultConfig();

		MINIGAMES_HUB = new Location(Bukkit.getWorld("Quarantine"), 4000.5, 71, -2499.5);
		createMatchmakers();

		setCommandExecutors();

		registerEvents();
	}

	private void setCommandExecutors() {
		getCommand("play").setExecutor(new PlayCommand());
	}

	private void createMatchmakers() {
		Consumer<Player> onPlayerLeaveLobbyOrGame = this::spawnPlayerAtHub;

		List<PvpGame> possibleMeleeMatches = new ArrayList<>(Melee.getPlugin().getMeleeGameManager().getGamesList());
		meleeMatchmaker = new Matchmaker("Melee", possibleMeleeMatches, onPlayerLeaveLobbyOrGame);

		List<PvpGame> possibleRabbitMatches = new ArrayList<>(Melee.getPlugin().getRabbitGameManager().getGamesList());
		rabbitMatchmaker = new Matchmaker("Rabbit", possibleRabbitMatches, onPlayerLeaveLobbyOrGame);

		List<PvpGame> possibleJailbreakMatches = new ArrayList<>(Jailbreak.getPlugin().getJailbreakGameManager().getGamesList());
		jailbreakMatchmaker = new Matchmaker("Jailbreak", possibleJailbreakMatches, onPlayerLeaveLobbyOrGame);

//		List<PvpGame> possibleBattleMatches = new ArrayList<>(Battle.getPlugin().getBattleGameManager().getGamesList());
//		battleMatchmaker = new Matchmaker("Battle", possibleBattleMatches, onPlayerLeaveLobbyOrGame);
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
		case "jailbreak":
			return jailbreakMatchmaker;
//		case "battle":
//			return battleMatchmaker;
		default:
			return null;
		}
	}
	
}
