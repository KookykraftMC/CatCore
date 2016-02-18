package com.kookykraftmc.commands.player;

import com.kookykraftmc.config.LobbyManager;
import com.kookykraftmc.config.MessageManager;
import com.kookykraftmc.config.MessageManager.PrefixType;
import com.kookykraftmc.commands.AbstractCommand;
import com.kookykraftmc.commands.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class SpawnCommand extends AbstractCommand {

    public SpawnCommand() {
        super("spawn", "");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException {

        //make sure that a player is executing the command
        if (!(sender instanceof Player)) {
            throw new CommandException("Only players can teleport to the spawn point");
        }

        final Player player = (Player) sender;

        //teleport the player to spawn
        player.teleport(LobbyManager.getInstance().getLobbySpawn());

        return true;
    }

    @Override
    public List<String> tabComplete(final CommandSender sender, final String[] args) {
        return new ArrayList<String>();
    }

}
