package com.kookykraftmc.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kookykraftmc.CatCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class PlayerUtils {

    private ArrayList<Player> onlinePlayers = new ArrayList<>();

    private static PlayerUtils instance = new PlayerUtils();

    public PlayerUtils() {

    }

    public static PlayerUtils getInstance() {
        return instance;
    }

    public void setupPlayerList() {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            onlinePlayers.add(player);
        }
    }

    /**
     * Get a list of players online
     *
     * @return A list of online players
     */
    public List<Player> getPlayers() {

        //clear our current list
        onlinePlayers.clear();
        setupPlayerList();

        //return the new list of online players
        return onlinePlayers;
    }

    @SuppressWarnings("deprecation")
    public OfflinePlayer getOfflinePlayer(String name) {
        return Bukkit.getOfflinePlayer(name);
    }



}
