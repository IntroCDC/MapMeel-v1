package br.com.introcdc.mapmeelv1.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.google.common.base.Preconditions;

import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class CommandComplete implements CommandExecutor {

    public static void sendTitle(final Player player, final String title, final String subtitle, final int fadeIn, final int duration, final int fadeOut) {
        Preconditions.checkNotNull(title, "Title cannot be null");
        Preconditions.checkNotNull(subtitle, "Subtitle cannot be null");
        final PacketPlayOutTitle timingsPacket = new PacketPlayOutTitle(EnumTitleAction.TIMES, null, fadeIn, duration, fadeOut);
        final PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(EnumTitleAction.TITLE, ChatSerializer.a("{\"text\":\"" + title + "\"}"));
        final PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, ChatSerializer.a("{\"text\":\"" + subtitle + "\"}"));
        final PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().playerConnection;
        playerConnection.sendPacket(timingsPacket);
        playerConnection.sendPacket(titlePacket);
        playerConnection.sendPacket(subtitlePacket);
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        for (final Player player : Bukkit.getOnlinePlayers()) {
            CommandComplete.sendTitle(player, "§2§lParabéns!", "§oVocê finalizou o MapMeel v1 com sucesso!", 20, 100, 20);
            player.sendMessage("§5§lMapMeel >> §2§lParabéns! §f§oVocê finalizou o MapMeel v1 com sucesso!");
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 50000, 1);
            player.playSound(player.getLocation(), "mapmeelv3.win", 50000, 1);
            System.out.println("MapMeelv1Complete-Abcas8125");
        }
        return false;
    }

}
