package net.punchtree.core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import net.punchtree.core.logging.MessageOfTheDay;

public class PunchtreeCore extends JavaPlugin {
	
	public static Plugin getPlugin(){
		return Bukkit.getPluginManager().getPlugin("Punchtree-Core");
	}
	
	public void onEnable(){
		saveDefaultConfig();
		Bukkit.getServer().getPluginManager().registerEvents(new MessageOfTheDay(), getPlugin());
	}
	
}
