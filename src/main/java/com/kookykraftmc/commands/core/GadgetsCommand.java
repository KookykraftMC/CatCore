package com.kookykraftmc.commands.core;

import com.kookykraftmc.commands.AbstractCommand;
import com.kookykraftmc.commands.CommandException;
import com.kookykraftmc.config.ConfigManager;
import com.kookykraftmc.config.MessageManager;
import com.kookykraftmc.config.MessageManager.PrefixType;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class GadgetsCommand extends AbstractCommand {

    public GadgetsCommand() {
        super("gadgets", "<enable|disable>");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException {

        if (args.length > 0) {

            if (args[0].equalsIgnoreCase("enable")) {

                //enable gadgets
                ConfigManager.getInstance().getConfig().set("gadgets", true);
                ConfigManager.getInstance().saveConfig();

                //reload config and announce that gadgets are enabled
                ConfigManager.getInstance().reloadConfig();
                MessageManager.getInstance().broadcastPrefixMessage(PrefixType.GADGET, "Gadgets have been enabled");

                return true;

            }

            if (args[0].equalsIgnoreCase("disable")) {

                //disable gadgets
                ConfigManager.getInstance().getConfig().set("gadgets", false);
                ConfigManager.getInstance().saveConfig();

                //reload config and announce that gadgets are enabled
                ConfigManager.getInstance().reloadConfig();
                MessageManager.getInstance().broadcastPrefixMessage(PrefixType.GADGET, "Gadgets have been disabled");

                return true;
            }

        }

        return false;

    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {

        List<String> toReturn = new ArrayList<>();

        if (args.length == 1) {

            if (sender.hasPermission("microcore.gadgets")) {
                toReturn.add("enable");
                toReturn.add("disable");
            }

        }

        return toReturn;

    }


}
