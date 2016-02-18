package com.kookykraftmc.commands;


import com.kookykraftmc.CatCore;
import com.kookykraftmc.commands.core.*;
import com.kookykraftmc.commands.player.*;
import com.kookykraftmc.config.MessageManager;
import com.kookykraftmc.config.MessageManager.PrefixType;
import com.kookykraftmc.utils.PlayerUtils;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler implements CommandExecutor, TabCompleter {

    private List<AbstractCommand> cmds = new ArrayList<>();
    private static CommandHandler instance = new CommandHandler();

    public CommandHandler() {

    }

    public static CommandHandler getInstance() {
        return instance;
    }

    /**
     * Register all the commands
     */
    public void registerCommands() {


        //core
        cmds.add(new GadgetsCommand());
        cmds.add(new CatbotCommand());

        //player
        cmds.add(new GamemodeCommand());


        for (AbstractCommand cmd : cmds) {

            PluginCommand pCmd = CatCore.getPlugin().getCommand(cmd.getName());

            // if its null, broadcast the command name so I know which one it is (so I can fix it).
            if (pCmd == null) {
                MessageManager.getInstance().broadcastFMessage(PrefixType.WARNING, "The command " + cmd.getName() + " is null");
                continue;
            }

            pCmd.setExecutor(this);
            pCmd.setTabCompleter(this);

        }

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        AbstractCommand command = getCommandExact(cmd.getName());

        if (command == null) {
            //this shouldn't happen, it only uses registered commands
            return true;
        }

        //if they don't have permission tell them so and stop.
        if (!sender.hasPermission(command.getPermission())) {
            MessageManager.getInstance().sendSenderPrefixMessage(PrefixType.PERMISSIONS, "Dev+ required to use this command", sender);
            return true;
        }

        try {
            //if the command returned false, send the usage.
            if (!command.execute(sender, args)) {
                MessageManager.getInstance().sendSenderPrefixMessage(PrefixType.MAIN, "Usage: " + command.getUsage(), sender);
            }
        } catch (CommandException ex) {
            MessageManager.getInstance().sendSenderPrefixMessage(PrefixType.ERROR, ex.getMessage(), sender);
        } catch (Exception ex) {
            MessageManager.getInstance().sendSenderPrefixMessage(PrefixType.ERROR, ex.getMessage(), sender);
            ex.printStackTrace();
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        AbstractCommand command = getCommandExact(cmd.getName());

        if (command == null) {
            //this shouldn't happen, it only uses registered commands
            return null;
        }

        //if they don't have permission, stop
        if (!sender.hasPermission(command.getPermission())) {
            return null;
        }

        try {
            List<String> list = command.tabComplete(sender, args);

            //if the list is null, replace it with everyone online
            if (list == null) {
                list = getAllPlayerNames(sender);
            }

            //if list is empty, don't do anything
            if (list.isEmpty()) {
                return list;
            }

            List<String> toReturn = new ArrayList<>();

            if (args[args.length - 1].isEmpty()) {
                for (String type : list) {
                    toReturn.add(type);
                }
            } else {
                for (String type : list) {
                    if (type.toLowerCase().startsWith(args[args.length - 1].toLowerCase())) {
                        toReturn.add(type);
                    }
                }
            }

            return toReturn;

        } catch (Exception ex) {
            //send them the error message if anything failed
            MessageManager.getInstance().sendSenderPrefixMessage(PrefixType.ERROR, ex.getMessage(), sender);
        }

        return null;
    }

    /**
     * Get the command
     *
     * @param name The name of the command
     * @return the AbstractCommand if found, null otherwise
     */
    protected AbstractCommand getCommandExact(String name) {
        for (AbstractCommand cmd : cmds) {
            if (cmd.getName().equalsIgnoreCase(name)) {
                return cmd;
            }
        }

        return null;
    }

    private List<String> getAllPlayerNames(CommandSender sender) {
        List<String> list = new ArrayList<>();

        for (Player online : PlayerUtils.getInstance().getPlayers()) {
            if (!(sender instanceof Player) || ((Player) sender).canSee(online)) {
                list.add(online.getName());
            }
        }

        return list;
    }

}
