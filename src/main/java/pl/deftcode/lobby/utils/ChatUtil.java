package pl.deftcode.lobby.utils;

import org.bukkit.ChatColor;

public class ChatUtil {

    public static String fixColor(String string){
        return ChatColor.translateAlternateColorCodes('&', string)
                .replace(">>", "»")
                .replace("<<", "«")
                .replace("{o}", "\u2022");
    }



}
