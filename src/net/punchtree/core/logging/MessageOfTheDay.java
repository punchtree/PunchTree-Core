package net.punchtree.core.logging;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import net.punchtree.core.PunchtreeCore;

public class MessageOfTheDay implements Listener{
											
	public static String getMessageOfTheDay(){
		return PunchtreeCore.getPlugin().getConfig().getString("Message Of The Day").replace("&", "§");
	}
	
	@EventHandler
	public void onServerPing(ServerListPingEvent e){
		e.setMotd(getMessageOfTheDay());
	}
	
	
}
