package com.kookykraftmc.commands;

import com.kookykraftmc.config.MessageManager;
import com.kookykraftmc.config.MessageManager.PrefixType;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OPCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args)
    {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (cmd.getName().equalsIgnoreCase("op")) {

                if (player.hasPermission("catcore.op")) {

                    if (args[0] != null) {

                        OfflinePlayer PlayerToOp = Bukkit.getOfflinePlayer(args[0]);

                        if (PlayerToOp != null) {
                            PlayerToOp.setOp(true);
                            MessageManager.getInstance().sendPrefixMessage(PrefixType.OP, "You have oped " + PlayerToOp.getName(), player);
                        } else {
                            MessageManager.getInstance().sendMessage(PrefixType.ERROR, "The player you are trying to op may have already been oped.", player);
                        }

                    } else {
                        MessageManager.getInstance().sendMessage(PrefixType.ERROR, "To op someone, please use /op [Player]", player);
                    }

                } else {

                    //player does not have permission to use /fly command
                    MessageManager.getInstance().sendPrefixMessage(PrefixType.PERMISSIONS, "Owner rank required to use /op", player);

                }

            }

        }

        return true;
    }
}
