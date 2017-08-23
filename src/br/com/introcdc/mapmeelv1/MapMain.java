package br.com.introcdc.mapmeelv1;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.introcdc.mapmeelv1.commands.CommandComplete;

public class MapMain extends JavaPlugin {

    private static Plugin plugin;

    public static Plugin getPlugin() {
        return MapMain.plugin;
    }

    @Override
    public void onEnable() {
        MapMain.plugin = this;
        this.getCommand("mapmeelv1complete").setExecutor(new CommandComplete());
        Bukkit.getPluginManager().registerEvents(new MapListeners(), MapMain.getPlugin());
    }

}
