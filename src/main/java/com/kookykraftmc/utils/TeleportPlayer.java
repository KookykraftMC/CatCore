package com.kookykraftmc.utils;

import com.frostynexus.FrostyNexusCore;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class TeleportPlayer {

    private static TeleportPlayer instance = new TeleportPlayer();

    public TeleportPlayer() {

    }

    public static TeleportPlayer getInstance() {
        return instance;
    }

    public void sendPlayerToServer(Player player, String targetServer) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(targetServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        player.sendPluginMessage(FrostyNexusCore.getPlugin(), "BungeeCord", b.toByteArray());
    }

}
