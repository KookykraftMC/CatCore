package com.kookykraftmc.commands.player;

import java.util.ArrayList;
import java.util.List;

import com.kookykraftmc.config.MessageManager;
import com.kookykraftmc.config.MessageManager.PrefixType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kookykraftmc.commands.AbstractCommand;
import com.kookykraftmc.commands.CommandException;

@SuppressWarnings("deprecation")
public class GamemodeCommand extends AbstractCommand {

    public GamemodeCommand() {
        super("gamemode", "<mode> [player]");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException {

        if (args.length == 0) {
            return false;
        }

        GameMode mode = null;

        try {
            mode = GameMode.getByValue(Integer.parseInt(args[0]));
        } catch (Exception e) {
            for (GameMode modes : GameMode.values()) {
                if (modes.name().startsWith(args[0].toUpperCase())) {
                    mode = modes;
                    break;
                }
            }
        }

        if (mode == null) {
            throw new CommandException("'" + args[0] + "' is not a valid gamemode.");
        }

        if (args.length == 1) {
            if (!(sender instanceof Player)) {
                throw new CommandException("Only players can change their gamemode");
            }

            Player player = (Player) sender;
            MessageManager.getInstance().sendPrefixMessage(PrefixType.GAMEMODE, "You are now in " + ChatColor.GOLD + mode.name().toLowerCase() + " mode", player);
            player.setGameMode(mode);
            return true;
        }

        Player target = Bukkit.getPlayer(args[1]);

        if (target == null) {
            throw new CommandException("'" + args[1] + "' is not online.");
        }

        MessageManager.getInstance().sendSenderPrefixMessage(PrefixType.GAMEMODE, "You have changed " +
                ChatColor.GREEN + target.getName()  + "'s" + ChatColor.GRAY + " gamemode to " +
                ChatColor.GOLD + mode.name().toLowerCase() + ChatColor.GRAY + " mode", sender);

        target.setGameMode(mode);
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        List<String> toReturn = new ArrayList<>();

        if (args.length == 1) {
            for (GameMode mode : GameMode.values()) {
                toReturn.add(mode.name());
            }
        }

        if (args.length == 2) {
            return null;
        }

        return toReturn;
    }

}
