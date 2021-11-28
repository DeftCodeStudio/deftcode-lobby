package pl.deftcode.lobby.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import pl.deftcode.lobby.flat.Messages;
import pl.deftcode.lobby.profile.LobbyProfile;
import pl.deftcode.lobby.profile.ProfileManager;
import pl.deftcode.lobby.utils.*;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){

        Player player = event.getPlayer();

        ProfileManager profileManager = ProfileManager.getInstance();
        if(profileManager.getProfile(player) == null){
            profileManager.createProfile(player);
        }

        event.setJoinMessage("");

        Bukkit.getOnlinePlayers().forEach(onlinePlayer -> {
            onlinePlayer.sendMessage("");
            onlinePlayer.sendMessage(ChatUtil.fixColor((player.hasPermission("lobby.admin")
                    ? Messages.ADMIN_JOIN_MESSAGE : Messages.PLAYER_JOIN_MESSAGE).replace("{PLAYER}", player.getName())));
            onlinePlayer.sendMessage("");
        });


        PlayerUtil.reset(player);
        player.getInventory().setContents(InventoryUtil.getLobbyInventory());
        player.updateInventory();

        TitleAPI.sendTitle(player, Messages.TITLE_MAIN, Messages.JOIN_SUBTITLE);

        TablistUtil.applyTab(player);

    }

    @EventHandler
    public void onHungerChange(FoodLevelChangeEvent event){ event.setCancelled(true); }

    @EventHandler
    public void onDamage(EntityDamageEvent event){ event.setCancelled(true); }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        event.setCancelled(true);

        TitleAPI.sendTitle(player, Messages.TITLE_MAIN, Messages.ANTYCHAT_SUBTITLE);
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event){
        Player player = event.getPlayer();
        LobbyProfile profile = ProfileManager.getInstance().getProfile(player);

        if(profile.isBuildMode())return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent event){
        Player player = event.getPlayer();
        LobbyProfile profile = ProfileManager.getInstance().getProfile(player);

        if(profile.isBuildMode())return;
        event.setCancelled(true);
    }




}
