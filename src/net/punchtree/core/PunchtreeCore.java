package net.punchtree.core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class PunchtreeCore extends JavaPlugin {
	
	public static Plugin getPlugin(){
		return Bukkit.getPluginManager().getPlugin("Punchtree-Core");
	}
	
}
