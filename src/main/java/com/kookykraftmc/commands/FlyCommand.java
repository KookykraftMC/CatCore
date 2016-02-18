package com.kookykraftmc.commands;

import com.frostynexus.utils.RankUtils;
import com.frostynexus.config.MessageManager;
import com.frostynexus.config.MessageManager.PrefixType;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args)
    {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (cmd.getName().equalsIgnoreCase("fly")) {

                if (RankUtils.getInstance().isDonator(player) || RankUtils.getInstance().isStaff(player) || RankUtils.getInstance().isMediaStaff(player)) {

                    if (!player.isFlying()) {

                        //activate fly mode for the player
                        player.setAllowFlight(true);
                        player.setFlying(true);
                        MessageManager.getInstance().sendMessage(PrefixType.INFO, "Fly mode enabled", player);

                    } else {

                        //disable fly mode for the player
                        player.setFlying(false);
                        player.setAllowFlight(false);
                        MessageManager.getInstance().sendMessage(PrefixType.INFO, "Fly mode disabled", player);

                    }

                } else {

                    //player does not have permission to use /fly command
                    MessageManager.getInstance().sendPrefixMessage(PrefixType.PERMISSIONS, "Emerald+, or Dev+ required to use /fly", player);

                }
            }

        }

        return true;
    }
}
