package com.kookykraftmc.utils;

import com.kookykraftmc.config.MessageManager;
import com.kookykraftmc.config.MessageManager.PrefixType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.OfflinePlayer;

public class WhitelistUtil {

    public static WhitelistUtil instance = new WhitelistUtil();

    public WhitelistUtil() {

    }

    public static WhitelistUtil getInstance() {
        return instance;
    }

    public void enableWhitelist(Player player) {

        //enables the whitelist
        if (!Bukkit.getServer().hasWhitelist()) {
            Bukkit.getServer().setWhitelist(true);
            MessageManager.getInstance().sendPrefixMessage(PrefixType.WHITELIST, "The whitelist has been enabled.", player);
        } else {
            MessageManager.getInstance().sendPrefixMessage(PrefixType.WHITELIST, "The whitelist is already enabled.", player);
        }

    }

    public void disableWhitelist(Player player) {

        //disables the server whitelist
        if (Bukkit.getServer().hasWhitelist()) {
            Bukkit.getServer().setWhitelist(false);
            MessageManager.getInstance().sendPrefixMessage(PrefixType.WHITELIST, "The whitelist has been disabled.", player);
        } else {
            MessageManager.getInstance().sendPrefixMessage(PrefixType.WHITELIST, "The whitelist is already disabled.", player);
        }

    }

    public void whitelistAll() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.setWhitelisted(true);
        }

        MessageManager.getInstance().broadcastPrefixMessage(PrefixType.WHITELIST, "All players have been whitelisted.");
    }

    public void clearWhitelist() {
        for (OfflinePlayer p : Bukkit.getWhitelistedPlayers()) {
            p.setWhitelisted(false);
        }

        MessageManager.getInstance().broadcastPrefixMessage(PrefixType.WHITELIST, "All players have been unwhitelisted.");
    }

    public void addPlayerToWhitelist(String name, Player player) {

        if (name != null) {
            OfflinePlayer requestedPlayer = Bukkit.getOfflinePlayer(name);

            if (requestedPlayer != null) {

                requestedPlayer.setWhitelisted(true);
                MessageManager.getInstance().sendPrefixMessage(PrefixType.WHITELIST, "The player " + name + " has been whitelisted!", player);

            } else {
                MessageManager.getInstance().sendPrefixMessage(PrefixType.WHITELIST, "The player provided is not valid.", player);
            }
        }


    }

    public void removePlayerFromWhitelist(String name, Player player) {

        if (name != null) {
            OfflinePlayer requestedPlayer = Bukkit.getOfflinePlayer(name);

            if (requestedPlayer != null) {

                if (requestedPlayer.isWhitelisted()) {
                    requestedPlayer.setWhitelisted(false);
                    MessageManager.getInstance().sendPrefixMessage(PrefixType.WHITELIST, "The player " + name + " has been removed from the whitelist!", player);
                } else {
                    MessageManager.getInstance().sendPrefixMessage(PrefixType.WHITELIST, "The player " + name + "could not be removed from the whitelist.", player);
                }

            } else {
                MessageManager.getInstance().sendPrefixMessage(PrefixType.WHITELIST, "The player provided is not valid.", player);
            }
        }
    }

}
