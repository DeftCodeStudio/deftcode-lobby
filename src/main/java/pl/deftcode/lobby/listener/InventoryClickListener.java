package pl.deftcode.lobby.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import pl.deftcode.lobby.flat.Messages;
import pl.deftcode.lobby.profile.LobbyProfile;
import pl.deftcode.lobby.profile.ProfileManager;
import pl.deftcode.lobby.server.ServerManager;
import pl.deftcode.lobby.server.ServerProfile;
import pl.deftcode.lobby.utils.BungeeUtil;
import pl.deftcode.lobby.utils.ChatUtil;
import pl.deftcode.lobby.utils.TitleAPI;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){

        Player player = (Player)event.getWhoClicked();
        LobbyProfile profile = ProfileManager.getInstance().getProfile(player);

        if(profile.isBuildMode())return;

        event.setCancelled(true);

        if(event.getClickedInventory() == null)return;
        if(event.getClickedInventory().getType().equals(InventoryType.PLAYER))return;
        if(!ChatUtil.fixColor(Messages.SERVER_SELECTOR_NAME).equalsIgnoreCase(event.getView().getTitle()))return;

        int slot = event.getSlot();

        ServerProfile serverProfile = ServerManager.getInstance().getServerBySlot(slot);

        if(serverProfile == null)return;

        player.closeInventory();
        TitleAPI.sendTitle(player,10, 300, 10,
                Messages.TITLE_MAIN, Messages.SERVER_CONNECTING_SUBTITLE.replace("{SERVER}", serverProfile.getName()));

        String server = serverProfile.getBungeeName();
        BungeeUtil.connectToServer(player, server);



    }



}
