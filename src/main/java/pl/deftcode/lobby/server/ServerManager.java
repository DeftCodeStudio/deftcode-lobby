package pl.deftcode.lobby.server;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.deftcode.lobby.gui.ServerSelectorGui;
import pl.deftcode.lobby.utils.ChatUtil;
import pl.deftcode.lobby.utils.ItemBuilder;

import java.util.ArrayList;
import java.util.List;

public class ServerManager {

    @Getter private List<ServerProfile> serverProfileList;
    @Getter private static ServerManager instance;

    public ServerManager(){
        instance = this;
        this.serverProfileList = new ArrayList<>();
    }

    public void createServerProfile(String name, String bungeeName
            , int slot, String title, int id, short data, List<String> lore){

        ItemStack inventoryItem = new ItemBuilder(Material.getMaterial(id), ChatUtil.fixColor(title),
                1, data, lore).getItem();

        ServerProfile serverProfile = new ServerProfile(name, bungeeName, slot, title, inventoryItem);
        this.getServerProfileList().add(serverProfile);

    }

    public void openServerSelectorGui(Player player){
        if(this.getServerProfileList().isEmpty()){
            player.sendMessage(ChatUtil.fixColor("&4Blad: &cNie znaleziono zadnych serwerow. Mozesz je dodac w pliku servers.yml"));
            return;
        }
        player.openInventory(ServerSelectorGui.getInstance().getInventory());
    }

    public ServerProfile getServerBySlot(int slot){
        return this.getServerProfileList().stream()
                .filter(profile -> profile.getInventorySlot() == slot).findFirst().orElse(null);
    }


}
