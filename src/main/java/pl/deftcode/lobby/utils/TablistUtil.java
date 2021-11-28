package pl.deftcode.lobby.utils;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import pl.deftcode.lobby.flat.Messages;

import java.lang.reflect.Field;

public class TablistUtil {

    public static void applyTab(Player player) {

        StringBuilder header = new StringBuilder();
        Messages.TAB_HEADER.forEach(string -> header.append(string).append("\n"));

        StringBuilder footer = new StringBuilder();
        Messages.TAB_FOOTER.forEach(string -> footer.append(string).append("\n"));

        TablistUtil.sendTabTitle(player, ChatUtil.fixColor(header.toString().replace("{PLAYER}", player.getName())),
                ChatUtil.fixColor(footer.toString().replace("{PLAYER}", player.getName())));

    }

    private static void sendTabTitle(Player player, String header, String footer) {

        header = header.replaceAll("%player%", player.getName());
        footer = footer.replaceAll("%player%", player.getName());

        PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
        IChatBaseComponent tabTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + header + "\"}");
        IChatBaseComponent tabFoot = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + footer + "\"}");
        PacketPlayOutPlayerListHeaderFooter headerPacket = new PacketPlayOutPlayerListHeaderFooter(tabTitle);

        try {
            Field field = headerPacket.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(headerPacket, tabFoot);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.sendPacket(headerPacket);
        }

    }

}
