package pl.deftcode.lobby.utils;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pl.deftcode.lobby.flat.Messages;

public class InventoryUtil {

    public static ItemStack[] getLobbyInventory(){
        return new ItemStack[] {
                PlayerItem.SERVER_SELECTOR.getItem(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                PlayerItem.FLYING_TOGGLE.getItem()
        };
    }

    public enum PlayerItem {

        SERVER_SELECTOR(new ItemBuilder(Material.COMPASS, ChatUtil.fixColor(Messages.SERVER_SELECTOR_NAME)).getItem()),
        FLYING_TOGGLE(new ItemBuilder(Material.FEATHER, ChatUtil.fixColor(Messages.FLY_ITEM_NAME)).getItem());

        @Getter private ItemStack item;

        PlayerItem(ItemStack item){
            this.item = item;
        }




    }



}
