package net.punchtree.core.logging;

import java.util.List;
import java.util.Random;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import net.punchtree.core.PunchtreeCore;

public class MessageOfTheDay implements Listener{
											
	public static String getMessageOfTheDay(){
		return PunchtreeCore.getPlugin().getConfig().getString("Message Of The Day").replace("&", "§");
	}
	
	public static String getSplash(){
		List<String> splashes = PunchtreeCore.getPlugin().getConfig().getStringList("Splashes"); 
		return splashes.get(new Random().nextInt(splashes.size()));
	}
	
	@EventHandler
	public void onServerPing(ServerListPingEvent e){
		e.setMotd(getMessageOfTheDay() + getSplash());
	}
	
}
