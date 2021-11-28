package pl.deftcode.lobby.flat;

import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import pl.deftcode.lobby.LobbyPlugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class Messages {


    public static String TITLE_MAIN = "";
    public static String PLAYER_JOIN_MESSAGE = "&8>> &7Gracz &6{PLAYER} &7dolaczyl na &6lobby&7!";
    public static String ADMIN_JOIN_MESSAGE = "&8>> &cAdministrator &4{PLAYER} &7dolaczyl na &clobby&7!";
    public static String JOIN_SUBTITLE = "&8>> &7Witamy na &6lobby";
    public static String SERVER_CONNECTING_SUBTITLE = "&8>> &7Trwa laczenie z serwerem &6{SERVER}&7...";
    public static List<String> TAB_HEADER = Arrays.asList(" ", "&fSERWER.PL", "&8[ &7Twoj serwer megadrop! &8]",
            "&8>> &7Witaj, &f{PLAYER}", " ", "   &e&lSERWER TWORZONY Z PASJA!   ");
    public static List<String> TAB_FOOTER = Arrays.asList(" ", "&7&lTEAMSPEAK", "&7&lTS.SERWER.PL");
    public static String ACTIONBAR_MESSAGE = "&8>> &7Witamy na &e&lLobby&7! - Jest godzina &6{HOUR} &8<<";
    public static String ANTYCHAT_SUBTITLE = "&8>> &7Chat na lobby jest &cwylaczony";
    public static String NO_PERMISSION_MESSAGE = "&4Blad: &cNie posiadasz uprawnien do tej komendy!";
    public static String SERVER_SELECTOR_NAME = "&8{o} &7Wybierz &6tryb &8{o}";
    public static String FLY_ITEM_NAME = "&8{o} &bLatanie &8{o}";
    public static String FLY_NO_PERMISSION_SUBTITLE = "&8>> &7Aby latac na lobby, zakup range &6VIP";
    public static String FLY_ENABLED_SUBTITLE = "&8>> &7Latanie zostalo &awlaczone";
    public static String FLY_DISABLED_SUBTITLE = "&8>> &7Latanie zostalo &cwylaczone";
    public static String BUILDMODE_ENABLED_SUBTITLE = "&8>> &7Tryb budowniczego zostal &awlaczony";
    public static String BUILDMODE_DISABLED_SUBTITLE = "&8>> &7Tryb budowniczego zostal &cwylaczony";


    private static FileConfiguration messages;

    @SneakyThrows
    public Messages(){

        File file = new File(LobbyPlugin.getPlugin().getDataFolder(), "messages.yml");

        if (!file.exists()) {
            file.getParentFile().mkdir();
            file.createNewFile();
            LobbyPlugin.getPlugin().saveResource("messages.yml", false);
        }

        messages = new YamlConfiguration();
        try {
            messages.load(file);
        } catch (Exception ex) {
            Bukkit.getLogger().severe("Podczas ladowania pliku konfiguracyjnego (messages.yml) wystapil blad!");
            Bukkit.getLogger().severe("Blad: " + ex.getMessage());
            Bukkit.getLogger().severe(" ");
        }

        for(Field field : this.getClass().getFields()){
            if(field.getType().equals(FileConfiguration.class))continue;
            String fieldName = field.getName();
            messages.set(fieldName, field.get(field.getType()));
        }

        messages.save(new File(LobbyPlugin.getPlugin().getDataFolder(), "messages.yml"));

    }

    public static void load() throws IllegalAccessException{
        for(Field field : Messages.class.getFields()){
            if(!field.getType().equals(FileConfiguration.class))
            field.set(field.getType(), messages.get(field.getName()));
        }
    }

    public static void reload() throws IOException, IllegalAccessException, InvalidConfigurationException {
        messages.load(new File(LobbyPlugin.getPlugin().getDataFolder(), "messages.yml"));
        load();
    }

}
