package pl.deftcode.lobby.task;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import pl.deftcode.lobby.flat.Messages;
import pl.deftcode.lobby.utils.ChatUtil;
import pl.deftcode.lobby.utils.TitleAPI;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActionbarRunnable extends BukkitRunnable {


    @Override
    public void run() {
        String date = new SimpleDateFormat("HH:mm:ss").format(new Date());
        Bukkit.getOnlinePlayers().forEach(player ->
                TitleAPI.sendActionBar(player, ChatUtil.fixColor(Messages.ACTIONBAR_MESSAGE.replace("{HOUR}", date))));

    }


}
