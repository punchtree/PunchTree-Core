package net.punchtree.core.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.punchtree.core.PunchTreeCorePlugin;
import net.punchtree.minigames.lobby.Matchmaker;

public class PlayCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (! (sender instanceof Player player) || !"play".equalsIgnoreCase(label) || args.length < 1) {
			return false;
		}

		Matchmaker matchmaker = PunchTreeCorePlugin.getInstance().getMatchmaker(args[0]);
		if (matchmaker == null) {
			player.sendMessage(ChatColor.RED + "There's no game named '" + args[0] + "' to play!");
			return true;
		}
		
		boolean success = matchmaker.findLobbyFor(player);
		if (!success) {
			player.sendMessage(ChatColor.RED + "No lobbies available!");
		}

		return true;
	}

}
