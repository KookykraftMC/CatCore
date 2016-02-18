package com.kookykraftmc.config;


import com.kookykraftmc.CatCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class LobbyManager {

    private static LobbyManager instance = new LobbyManager();
    private static ConfigManager configManager = ConfigManager.getInstance();
    private static Plugin p = CatCore.getPlugin();

    //lobby location
    private String worldName;
    private Double locationX;
    private Double locationZ;
    private Double locationY;
    private boolean LobbySet;

    public LobbyManager() {

    }

    public static LobbyManager getInstance() {
        return instance;
    }

    public void  setup() {
        LobbySet = configManager.getLobbyConfig().getBoolean("lobby-set");
        worldName = configManager.getLobbyConfig().getString("lobby-world");
        locationX = configManager.getLobbyConfig().getDouble("lobby-spawn.x");
        locationZ = configManager.getLobbyConfig().getDouble("lobby-spawn.z");
        locationY = configManager.getLobbyConfig().getDouble("lobby-spawn.y");
    }

    public boolean isLobbySet() {
        return LobbySet;
    }

    public Location getLobbySpawn() {
        try {

            return new Location(p.getServer().getWorld(worldName), locationX, locationY, locationZ);

        } catch (Exception e) {
            return null;
        }
    }

    public boolean setLobbySpawn(Location playerLocation) {
        try {
            //set our location for the lobby spawn
            configManager.getLobbyConfig().set("lobby-set", true);
            configManager.getLobbyConfig().set("lobby-world", playerLocation.getWorld().getName());
            configManager.getLobbyConfig().set("lobby-spawn.x", playerLocation.getX());
            configManager.getLobbyConfig().set("lobby-spawn.y", playerLocation.getY());
            configManager.getLobbyConfig().set("lobby-spawn.z", playerLocation.getZ());

            //save our lobby config file
            configManager.saveLobbyConfig();

            //reload our lobby config file
            configManager.reloadLobbyConfig();
            setup();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
