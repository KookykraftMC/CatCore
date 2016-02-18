 package com.kookykraftmc.commands;


 import com.kookykraftmc.config.MessageManager;
 import com.kookykraftmc.config.MessageManager.PrefixType;
 import com.kookykraftmc.utils.WhitelistUtil;
 import org.bukkit.Bukkit;
 import org.bukkit.ChatColor;
 import org.bukkit.OfflinePlayer;
 import org.bukkit.command.Command;
 import org.bukkit.command.CommandExecutor;
 import org.bukkit.command.CommandSender;
 import org.bukkit.entity.Player;

 public class WhitelistCommand implements CommandExecutor
 {
   public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {

       if (sender instanceof Player) {

           Player player = (Player) sender;

           if (command.getName().equalsIgnoreCase("wl")) {

               if (args == null || args.length < 1) {
                   MessageManager.getInstance().sendMessage(PrefixType.INFO, "usage: /wl <add|remove|addall|on|off> [player]", player);
                   return true;
               }


               if (args[0].equalsIgnoreCase("add")) {

                       if (args[1] != null) {

                           if (player.isOp()) {

                               WhitelistUtil.getInstance().addPlayerToWhitelist(args[1], player);

                           } else {
                               MessageManager.getInstance().sendPrefixMessage(PrefixType.PERMISSIONS, "You do not have permissions to whitelist users", player);
                           }

                       } else {
                           MessageManager.getInstance().sendMessage(PrefixType.ERROR, "To whitelist someone, please use /wl add [Player]", player);
                       }

               }

               if (args[0].equalsIgnoreCase("remove")) {

                   if (args[1] != null) {

                       if (player.isOp()) {

                           WhitelistUtil.getInstance().removePlayerFromWhitelist(args[1], player);

                       } else {
                           MessageManager.getInstance().sendPrefixMessage(PrefixType.PERMISSIONS, "You do not have permissions to unwhitelist users", player);
                       }

                   } else {
                       MessageManager.getInstance().sendMessage(PrefixType.ERROR, "To unwhitelist someone, please use /wl remove [Player]", player);
                   }

               }

               if (args[0].equalsIgnoreCase("addall")) {

                   if (player.isOp()) {

                       WhitelistUtil.getInstance().whitelistAll();

                   } else {
                       MessageManager.getInstance().sendPrefixMessage(PrefixType.PERMISSIONS, "You do not have permissions to unwhitelist users", player);
                   }

               }

               if (args[0].equalsIgnoreCase("clear")) {

                   if (player.isOp()) {

                       WhitelistUtil.getInstance().clearWhitelist();

                   } else {
                       MessageManager.getInstance().sendPrefixMessage(PrefixType.PERMISSIONS, "You do not have permissions to unwhitelist users", player);
                   }

               }


               if (args[0].equalsIgnoreCase("on")) {

                   if (player.isOp()) {

                       WhitelistUtil.getInstance().enableWhitelist(player);

                   } else {
                       MessageManager.getInstance().sendPrefixMessage(PrefixType.PERMISSIONS, "You do not have permissions to enable the unwhitelist", player);
                   }

               }

               if (args[0].equalsIgnoreCase("off")) {

                   if (player.isOp()) {

                        WhitelistUtil.getInstance().disableWhitelist(player);

                   } else {
                       MessageManager.getInstance().sendPrefixMessage(PrefixType.PERMISSIONS, "You do not have permissions to disable the unwhitelist", player);
                   }

               }

           }

       }

       return true;
   }

 }


