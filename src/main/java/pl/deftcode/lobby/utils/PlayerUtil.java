package pl.deftcode.lobby.utils;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerUtil {

    public static void reset(Player player){
        player.setHealth(20.0D);
        player.setSaturation(20.0F);
        player.setFoodLevel(20);
        player.setFireTicks(0);
        player.setExp(0.0F);
        player.setLevel(0);
        player.setAllowFlight(false);
        player.setFlying(false);
        player.setGameMode(GameMode.SURVIVAL);
        player.getInventory().setArmorContents(new ItemStack[4]);
        player.getInventory().setContents(new ItemStack[36]);
        player.getActivePotionEffects().forEach(effect -> player.removePotionEffect(effect.getType()));
        player.getInventory().setHeldItemSlot(0);
        player.updateInventory();
    }


}
