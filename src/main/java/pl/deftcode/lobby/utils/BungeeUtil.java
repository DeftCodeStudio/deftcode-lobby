package pl.deftcode.lobby.utils;

import org.bukkit.entity.Player;
import pl.deftcode.lobby.LobbyPlugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class BungeeUtil {

    public static void connectToServer(Player player, String server){
        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            out.writeUTF("Connect");
            out.writeUTF(server);
            player.sendPluginMessage(LobbyPlugin.getPlugin(), "BungeeCord", b.toByteArray());
            b.close();
            out.close();
        } catch (Exception e) {
            player.sendMessage(ChatUtil.fixColor("&cWystapil blad podczas laczenia z serwerem."));
            TitleAPI.clearTitle(player);
            e.printStackTrace();
        }
    }

}
