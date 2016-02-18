package com.kookykraftmc.commands;


import com.kookykraftmc.config.LobbyManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.kookykraftmc.config.ConfigManager;
import com.kookykraftmc.config.MessageManager;
import com.kookykraftmc.config.MessageManager.PrefixType;
import org.bukkit.entity.Player;

public class SetLobbyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player)
        {

            Player player = (Player) sender;

            //check permissions
            if (player.hasPermission("catcore.admin.setlobby")) {

                if (LobbyManager.getInstance().setLobbySpawn(player.getLocation())) {

                    //lobby was successfully set
                    MessageManager.getInstance().sendMessage(PrefixType.INFO, "lobby spawn successfully set.", player);

                    //reload lobby config and get the new lobby spawn values
                    ConfigManager.getInstance().reloadLobbyConfig();
                    LobbyManager.getInstance().setup();
                    return true;

                } else  {

                    MessageManager.getInstance().sendPrefixMessage(PrefixType.ERROR, "An error occured while setting the lobby", player);
                    return true;

                }

            }

            MessageManager.getInstance().sendPrefixMessage(PrefixType.PERMISSIONS, "You must be Dev+ to set the lobby spawn.", player);
            return true;

        }

        return false;
    }

}
