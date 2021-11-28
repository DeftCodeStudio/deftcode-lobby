package pl.deftcode.lobby.profile;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.deftcode.lobby.utils.TablistUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProfileManager {

    @Getter private List<LobbyProfile> lobbyProfileList;
    @Getter private static ProfileManager instance;

    public ProfileManager(){
        instance = this;
        this.lobbyProfileList = new ArrayList<>();
        Bukkit.getOnlinePlayers().forEach(player -> {
            this.createProfile(player);
            TablistUtil.applyTab(player);
        });
    }

    public void createProfile(Player player){
        LobbyProfile lobbyProfile = new LobbyProfile(player);
        this.getLobbyProfileList().add(lobbyProfile);
    }

    public LobbyProfile getProfile(Player player){
        return this.getLobbyProfileList().stream().
                filter(profile -> profile.getUuid().equals(player.getUniqueId())).findFirst().orElse(null);
    }

    public LobbyProfile getProfile(UUID uuid){
        return this.getLobbyProfileList().stream().
                filter(profile -> profile.getUuid().equals(uuid)).findFirst().orElse(null);
    }




}
