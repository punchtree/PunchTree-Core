package net.punchtree.core.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.punchtree.minigames.lobby.Matchmaker;

public class PlayCommand implements CommandExecutor {

	Matchmaker meleeMatchmaker;
	public PlayCommand(Matchmaker meleeMatchmaker) {
		this.meleeMatchmaker = meleeMatchmaker;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (! (sender instanceof Player player) || !"play".equalsIgnoreCase(label)) {
			return false;
		}

		boolean success = meleeMatchmaker.findLobbyFor(player);
		if (!success) {
			player.sendMessage(ChatColor.RED + "No lobbies available!");
		}

		return true;
	}

}
