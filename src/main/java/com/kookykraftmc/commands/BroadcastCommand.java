package com.kookykraftmc.commands;

import com.kookykraftmc.config.MessageManager;
import com.kookykraftmc.config.MessageManager.PrefixType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args)
    {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (cmd.getName().equalsIgnoreCase("broadcast")) {

                if (player.hasPermission("catcore.broadcast")) {

                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < args.length; i++) {
                        builder.append(args[i]).append(" ");
                    }
                    String msg = builder.toString();

                    if (!msg.isEmpty()) {
                        MessageManager.getInstance().broadcastMessage(PrefixType.INFO, msg);
                    } else {
                        MessageManager.getInstance().sendMessage(PrefixType.ERROR, "No message provided, please use /broadcast <message>", player);
                    }

                } else {

                    //player does not have permission to use /fly command
                    MessageManager.getInstance().sendPrefixMessage(PrefixType.PERMISSIONS, "Dev+ required to use /broadcast", player);

                }
            }

        }

        return true;
    }
}
