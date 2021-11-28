package pl.deftcode.lobby.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import pl.deftcode.lobby.profile.LobbyProfile;
import pl.deftcode.lobby.profile.ProfileManager;
import pl.deftcode.lobby.server.ServerManager;
import pl.deftcode.lobby.utils.InventoryUtil;

import java.util.Arrays;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event){

        Player player = event.getPlayer();
        LobbyProfile profile = ProfileManager.getInstance().getProfile(player);

        if(profile.isBuildMode())return;

        event.setCancelled(true);

        if(Arrays.asList(Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK).contains(event.getAction())){

            ItemStack handItem = player.getInventory().getItemInHand();

            if (handItem.equals(InventoryUtil.PlayerItem.FLYING_TOGGLE.getItem())) { profile.toggleFlying(); }
            else if (handItem.equals(InventoryUtil.PlayerItem.SERVER_SELECTOR.getItem())) {
                ServerManager.getInstance().openServerSelectorGui(player);
            }

        }



    }



}
