package com.kookykraftmc.commands.core;

import com.kookykraftmc.config.ConfigManager;
import com.kookykraftmc.config.MessageManager;
import com.kookykraftmc.config.MessageManager.PrefixType;
import com.kookykraftmc.commands.AbstractCommand;
import com.kookykraftmc.commands.CommandException;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CatbotCommand extends AbstractCommand {

    public CatbotCommand() {
        super("catbot", "<reload|mute|warn>");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException {

        if (!(sender instanceof Player)) {
            throw new CommandException("Only players can use this command");
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            MessageManager.getInstance().sendPrefixMessage(PrefixType.CATBOT, "Meow.", player);
            return false;
        }

        switch (args[0]) {
            case "reload":
                ConfigManager.getInstance().reloadCatbotConfig();
                MessageManager.getInstance().sendPrefixMessage(PrefixType.CATBOT, "Catbot reloaded. Meow.", player);
                break;
            case "mute":
                if (ConfigManager.getInstance().getCatbotConfig().getBoolean("ChatEnabled")) {

                    //global chat is enabled, disable it
                    ConfigManager.getInstance().getCatbotConfig().set("ChatEnabled", false);
                    MessageManager.getInstance().broadcastPrefixMessage(PrefixType.CATBOT, "Global chat has been disabled. Meow.");

                } else {

                    //global chat is disabled, enable it
                    ConfigManager.getInstance().getCatbotConfig().set("ChatEnabled", true);
                    MessageManager.getInstance().broadcastPrefixMessage(PrefixType.CATBOT, "Global chat has been enabled. Meow.");

                }
                break;
            case "warn":
                //Check all required arguments are present
                if (args.length < 2) {
                    MessageManager.getInstance().sendPrefixMessage(PrefixType.CATBOT, "Usage: /catbot warn <player>", player);
                    return true;
                }
                //Check that the player is online and send message
                Player targetToWarn = Bukkit.getPlayer(args[1]);
                if (targetToWarn == null) {
                    MessageManager.getInstance().sendPrefixMessage(PrefixType.CATBOT, "Player not found", player);
                    return true;
                }
                //tell staff CatBot is warning the player, and warn the player
                MessageManager.getInstance().sendPrefixMessage(PrefixType.CATBOT, "Warning " + args[1] + " about their language.", player);
                MessageManager.getInstance().sendPrefixMessage(PrefixType.CATBOT, "Please mind your language, if you continue to bypass catbot you will be muted.", targetToWarn);
                break;
            default:
                MessageManager.getInstance().sendPrefixMessage(PrefixType.CATBOT, "Meow.", player);
                return false;
        }

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        List<String> toReturn = new ArrayList<>();

        if (args.length == 1) {
            toReturn.add("reload");
            toReturn.add("mute");
            toReturn.add("warn");
        }

        if (args.length == 2) {
            return null;
        }

        return toReturn;
    }

}
