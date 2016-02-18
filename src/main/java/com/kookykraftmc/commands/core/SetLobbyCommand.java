package com.kookykraftmc.commands.core;

import com.kookykraftmc.config.LobbyManager;
import com.kookykraftmc.config.MessageManager;
import com.kookykraftmc.config.MessageManager.PrefixType;
import com.kookykraftmc.commands.AbstractCommand;
import com.kookykraftmc.commands.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SetLobbyCommand extends AbstractCommand {

    public SetLobbyCommand() {
        super("setlobby", "");
    }

    public boolean execute(CommandSender sender, String[] args) throws CommandException {

        //make sure that a player is executing the command
        if (!(sender instanceof Player)) {
            throw new CommandException("Only players can set the lobby spawn point");
        }

        final Player player = (Player) sender;

        if (LobbyManager.getInstance().setLobbySpawn(player.getLocation())) {
            MessageManager.getInstance().sendPrefixMessage(PrefixType.LOBBY, "Lobby spawn has been set successfully!", player);
            return true;
        } else {
            MessageManager.getInstance().sendPrefixMessage(PrefixType.LOBBY, "An error occured while attempting to set the lobby spawn.", player);
            return false;
        }
    }

    @Override
    public List<String> tabComplete(final CommandSender sender, final String[] args) {
        return new ArrayList<String>();
    }

}
