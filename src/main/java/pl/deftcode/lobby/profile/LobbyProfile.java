package pl.deftcode.lobby.profile;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.deftcode.lobby.flat.Messages;
import pl.deftcode.lobby.utils.TitleAPI;

import java.util.UUID;

public class LobbyProfile {

    @Getter private UUID uuid;
    @Getter private boolean flying;
    @Getter private boolean buildMode;

    public LobbyProfile(Player player){
        this.uuid = player.getUniqueId();
        this.flying = false;
        this.buildMode = false;
    }

    public void toggleFlying(){

        Player player = Bukkit.getPlayer(this.getUuid());
        if(player==null)return;

        if(!player.hasPermission("lobby.fly")){
            TitleAPI.sendTitle(player, Messages.TITLE_MAIN, Messages.FLY_NO_PERMISSION_SUBTITLE);
            return;
        }

        this.flying = !this.flying;
        player.setAllowFlight(this.flying);
        player.setFlying(this.flying);

        TitleAPI.sendTitle(player, Messages.TITLE_MAIN,
                this.isFlying() ? Messages.FLY_ENABLED_SUBTITLE : Messages.FLY_DISABLED_SUBTITLE);

    }

    public void toggleBuildMode(){
        this.buildMode = !this.buildMode;
    }




}
