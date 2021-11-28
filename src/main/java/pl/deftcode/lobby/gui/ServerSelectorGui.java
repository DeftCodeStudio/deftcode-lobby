package pl.deftcode.lobby.gui;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.deftcode.lobby.flat.Messages;
import pl.deftcode.lobby.server.ServerManager;
import pl.deftcode.lobby.server.ServerProfile;
import pl.deftcode.lobby.utils.ChatUtil;
import pl.deftcode.lobby.utils.ItemBuilder;

import java.util.Collections;

public class ServerSelectorGui {

    @Getter private Inventory inventory;
    @Getter private static ServerSelectorGui instance;

    public ServerSelectorGui(){

        instance = this;
        this.inventory = Bukkit.createInventory(null, 27, ChatUtil.fixColor(Messages.SERVER_SELECTOR_NAME));

        ItemStack whitePane = new ItemBuilder(Material.STAINED_GLASS_PANE, "").getItem();
        ItemStack greyPane = new ItemBuilder(Material.STAINED_GLASS_PANE, "", (short)8).getItem();

        for(int i = 0; i < 27; i++) {
            if (i%2==0) { this.inventory.setItem(i, whitePane); }
            else { this.inventory.setItem(i, greyPane); }
        }

        ServerManager serverManager = ServerManager.getInstance();

        if(serverManager.getServerProfileList().isEmpty())return;

        for(ServerProfile serverProfile : serverManager.getServerProfileList()){

            int slot = serverProfile.getInventorySlot();
            ItemStack item = serverProfile.getInventoryItem();

            this.inventory.setItem(slot, item);

        }

    }



}
