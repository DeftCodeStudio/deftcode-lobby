package pl.deftcode.lobby;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.deftcode.lobby.command.BuildModeCommand;
import pl.deftcode.lobby.command.ConfigReloadCommand;
import pl.deftcode.lobby.command.FlyCommand;
import pl.deftcode.lobby.flat.Messages;
import pl.deftcode.lobby.flat.ServerSelectorSettings;
import pl.deftcode.lobby.gui.ServerSelectorGui;
import pl.deftcode.lobby.listener.*;
import pl.deftcode.lobby.profile.ProfileManager;
import pl.deftcode.lobby.server.ServerManager;
import pl.deftcode.lobby.task.ActionbarRunnable;

public class LobbyPlugin extends JavaPlugin {

    @Getter private static LobbyPlugin plugin;

    public void onEnable(){
        long startTime = System.currentTimeMillis();

        plugin = this;

        new ServerManager();
        new ProfileManager();

        this.loadConfiguration();
        this.loadListeners();
        this.loadCommands();
        this.loadTasks();

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        new ServerSelectorGui();

        long loadTime = System.currentTimeMillis() - startTime;
        getLogger().info("Zaladowano plugin w " + loadTime + "ms");
    }


    private void loadConfiguration(){
        new Messages();
        new ServerSelectorSettings();
    }

    private void loadListeners(){
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerListener(), this);
        pluginManager.registerEvents(new PlayerInteractListener(), this);
        pluginManager.registerEvents(new InventoryClickListener(), this);

    }

    private void loadCommands(){
        SimpleCommandMap commandMap = ((CraftServer)getServer()).getCommandMap();
        commandMap.register("build", new BuildModeCommand("build"));
        commandMap.register("config", new ConfigReloadCommand("config"));
        commandMap.register("fly", new FlyCommand("fly"));
    }

    private void loadTasks(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new ActionbarRunnable(), 0, 20);
    }


}
