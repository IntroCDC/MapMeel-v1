package br.com.introcdc.mapmeelv1;

import br.com.introcdc.mapmeelv1.commands.CommandComplete;
import com.google.common.base.Preconditions;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class MapMain extends JavaPlugin {

    private static Plugin plugin;

    public static Plugin getPlugin() {
        return MapMain.plugin;
    }

    @Override
    public void onEnable() {
        MapMain.plugin = this;
        getCommand("mapmeelv1complete").setExecutor(new CommandComplete());
        Bukkit.getPluginManager().registerEvents(new MapListeners(), MapMain.getPlugin());
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "kindome:pm");
    }

    public static void sendPlayer(Player player, String server) {
        sendPluginMessage(player, "Connect", server);
    }

    public static void sendPluginMessage(Player player, String... messages) {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        DataOutputStream output = new DataOutputStream(byteArray);
        try {
            for (String message : messages) {
                output.writeUTF(message);
            }
        } catch (IOException ignored) {
        }
        player.sendPluginMessage(getPlugin(), "kindome:pm", byteArray.toByteArray());
    }

    public static void sendTitle(Player player, String title, String subtitle, int fadeIn, int duration, int fadeOut) {
        Preconditions.checkNotNull(title, "Title cannot be null");
        Preconditions.checkNotNull(subtitle, "Subtitle cannot be null");
        PacketPlayOutTitle timingsPacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeIn, duration, fadeOut);
        PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + title + "\"}"));
        PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + subtitle + "\"}"));
        PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().playerConnection;
        playerConnection.sendPacket(timingsPacket);
        playerConnection.sendPacket(titlePacket);
        playerConnection.sendPacket(subtitlePacket);
    }

}
