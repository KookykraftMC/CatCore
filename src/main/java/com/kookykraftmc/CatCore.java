package com.kookykraftmc;

import com.kookykraftmc.commands.*;
import com.kookykraftmc.config.ConfigManager;
import com.kookykraftmc.config.LobbyManager;
import com.kookykraftmc.config.MessageManager;
import com.kookykraftmc.utils.LogUtil;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

public class CatCore extends JavaPlugin {

    private static CatCore p;
    private String pluginChannel = "BungeeCord";

    public void onEnable() {
        p = this;

        //setup our managers
        ConfigManager.getInstance().setup();
        MessageManager.getInstance().setup();
        LobbyManager.getInstance().setup();

        //setup our economy handler
        //TODO: vault integration for KTokens

        //register any listeners we may need
        //TODO: Join listeners, etc

        //finally, we register commands
        registerCommands();

        CommandHandler.getInstance().registerCommands();
    }

    public void onDisable() {

    }

    public static CatCore getPlugin() {
        return p;
    }

    private void logStartup() {
        LogUtil.LogToConsole("");
        LogUtil.LogToConsole(ChatColor.BLUE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        LogUtil.LogToConsole("");
        LogUtil.LogToConsole(ChatColor.AQUA + "CatCore");
        LogUtil.LogToConsole(ChatColor.AQUA + "Version " + getDescription().getVersion());
        LogUtil.LogToConsole("");
        LogUtil.LogToConsole(ChatColor.BLUE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        LogUtil.LogToConsole("");
    }

    public void registerCommands() {
        //register plugin commands
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("broadcast").setExecutor(new BroadcastCommand());
        getCommand("wl").setExecutor(new WhitelistCommand());
        getCommand("op").setExecutor(new OPCommand());
        getCommand("deop").setExecutor(new DEOPCommand());
        getCommand("setlobby").setExecutor(new SetLobbyCommand());
    }
}
