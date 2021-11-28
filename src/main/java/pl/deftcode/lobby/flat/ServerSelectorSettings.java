package pl.deftcode.lobby.flat;

import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import pl.deftcode.lobby.LobbyPlugin;
import pl.deftcode.lobby.server.ServerManager;

import java.io.*;
import java.util.List;
import java.util.Set;

public class ServerSelectorSettings {

    private static FileConfiguration config;

    @SneakyThrows
    public ServerSelectorSettings(){

        File file = new File(LobbyPlugin.getPlugin().getDataFolder(), "servers.yml");

        if (!file.exists()) {
            file.getParentFile().mkdir();
            file.createNewFile();
            LobbyPlugin.getPlugin().saveResource("servers.yml", false);
            this.copyDefaultConfig("servers.yml", file);
        }

        config = YamlConfiguration.loadConfiguration(file);

        try {
            config.load(file);
            loadServerProfiles();
        } catch (Exception ex) {
            Bukkit.getLogger().severe("Podczas ladowania pliku konfiguracyjnego (servers.yml) wystapil blad!");
            Bukkit.getLogger().severe("Blad: " + ex.getMessage());
            Bukkit.getLogger().severe(" ");
        }
    }

    public static void reload() throws IOException, InvalidConfigurationException {
        config.load(new File(LobbyPlugin.getPlugin().getDataFolder(), "servers.yml"));
        loadServerProfiles();
    }

    private static void loadServerProfiles(){

        ServerManager.getInstance().getServerProfileList().clear();

        Set<String> servers = config.getConfigurationSection("servers").getKeys(false);
        if(servers.isEmpty())return;

        for(String server : servers) {

            String name = config.getString("servers." + server + ".name");
            String bungeeName = config.getString("servers." + server + ".bungeename");
            int slot = config.getInt("servers." + server + ".inventory.slot");
            String title = config.getString("servers." + server + ".inventory.title");
            String itemString = config.getString("servers." + server + ".inventory.item");
            List<String> lore = config.getStringList("servers." + server + ".inventory.lore");

            int materialId = Integer.parseInt(itemString.split(":")[0]);
            short materialData = Short.parseShort(itemString.split(":")[1]);

            ServerManager serverManager = ServerManager.getInstance();
            serverManager.createServerProfile(name, bungeeName, slot, title, materialId, materialData, lore);

        }
    }

    private void copyDefaultConfig(String ressourceFile, File targetFile) {
        try {
            InputStream ressource = LobbyPlugin.getPlugin().getResource(ressourceFile);
            OutputStream out = new FileOutputStream(targetFile);
            byte[] buf = new byte[1024];
            int len;
            while((len=ressource.read(buf))>0) {
                out.write(buf,0,len);
            }
            out.close();
            ressource.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }


}
