package br.com.introcdc.mapmeelv1.commands;

import br.com.introcdc.mapmeelv1.MapMain;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CommandComplete implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            MapMain.sendTitle(player, "§2§lParabéns!", "§oVocê finalizou o MapMeel v1 com sucesso!", 20, 100, 20);
            player.sendMessage("§5§lMapMeel >> §2§lParabéns! §f§oVocê finalizou o MapMeel v1 com sucesso!");
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 50000, 1);
            player.playSound(player.getLocation(), "mapmeelv3.win", 50000, 1);
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    MapMain.sendPlayer(player, "v2");
                }
            }
        }.runTaskLater(MapMain.getPlugin(), 100);
        return false;
    }

}
