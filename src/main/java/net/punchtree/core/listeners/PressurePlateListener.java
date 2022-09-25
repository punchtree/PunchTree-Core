package net.punchtree.core.listeners;

import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import net.punchtree.core.PunchTreeCorePlugin;
import net.punchtree.minigames.lobby.Matchmaker;

public class PressurePlateListener implements Listener {

	private static final String PLAY_SIGN_KEY = "::play::";
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteractPhysically(PlayerInteractEvent e) {
		if (e.getAction() != Action.PHYSICAL) {
			return;
		}

		Player player = e.getPlayer();
		Location potentialSign = player.getLocation().subtract(0, 1.5, 0);
		if ( ! (potentialSign.getBlock().getState() instanceof Sign sign)) { 
			return;
		}
		
		if (!sign.getLine(0).equalsIgnoreCase(PLAY_SIGN_KEY)) return;
			
		Matchmaker matchmaker = PunchTreeCorePlugin.getInstance().getMatchmaker(sign.getLine(1));
		if (matchmaker == null) return;
		
		matchmaker.findLobbyFor(player);
	}
	
}
