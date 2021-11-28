package pl.deftcode.lobby.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
@Getter
public class ServerProfile {

    private String name;
    private String bungeeName;
    private int inventorySlot;
    private String inventoryTitle;
    private ItemStack inventoryItem;

}
