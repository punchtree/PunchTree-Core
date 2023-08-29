package net.punchtree.core.logging;

import java.util.List;
import java.util.Random;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import net.punchtree.core.PunchTreeCorePlugin;

public class MessageOfTheDay implements Listener{

	// TODO convert this to just legacy deserialize with adventure the config message, instead of manually replacing
	public static String getMessageOfTheDay(){
		return PunchTreeCorePlugin.getInstance().getConfig().getString("Message Of The Day").replace("&", "§").replace("\\§", "&");
	}

	public static String getSplash(){
		List<String> splashes = PunchTreeCorePlugin.getInstance().getConfig().getStringList("Splashes");
		return splashes.get(new Random().nextInt(splashes.size()));
	}

	@EventHandler
	public void onServerPing(ServerListPingEvent event){
		event.motd(Component.text(getMessageOfTheDay() + "\n" + ChatColor.DARK_GRAY + getSplash()));
	}

}
