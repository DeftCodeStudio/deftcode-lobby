package pl.deftcode.lobby.command;

import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import pl.deftcode.lobby.flat.Messages;
import pl.deftcode.lobby.profile.LobbyProfile;
import pl.deftcode.lobby.profile.ProfileManager;
import pl.deftcode.lobby.utils.ChatUtil;
import pl.deftcode.lobby.utils.InventoryUtil;
import pl.deftcode.lobby.utils.PlayerUtil;
import pl.deftcode.lobby.utils.TitleAPI;

import java.util.Collections;

public class BuildModeCommand extends BukkitCommand {

    public BuildModeCommand(String name){
        super(name);
        this.setAliases(Collections.singletonList("bud"));
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage("Komenda dostepna wylacznie dla graczy!");
            return false;
        }

        Player player = (Player)sender;

        if(!player.hasPermission("lobby.build")){
            player.sendMessage(ChatUtil.fixColor(Messages.NO_PERMISSION_MESSAGE));
            return false;
        }

        LobbyProfile profile = ProfileManager.getInstance().getProfile(player);
        profile.toggleBuildMode();

        PlayerUtil.reset(player);

        if(!profile.isBuildMode()){
            player.getInventory().setContents(InventoryUtil.getLobbyInventory());
            player.getInventory().setArmorContents(null);
        } else {
            player.setGameMode(GameMode.CREATIVE);
        }

        TitleAPI.sendTitle(player, Messages.TITLE_MAIN,
                profile.isBuildMode() ? Messages.BUILDMODE_ENABLED_SUBTITLE : Messages.BUILDMODE_DISABLED_SUBTITLE);


        return false;
    }
}
