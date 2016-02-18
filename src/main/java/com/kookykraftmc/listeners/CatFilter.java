package com.kookykraftmc.listeners;

import com.kookykraftmc.config.ConfigManager;
import com.kookykraftmc.config.MessageManager;
import com.kookykraftmc.config.MessageManager.PrefixType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;
import java.util.Random;

public class CatFilter implements Listener {

    private List<String> badWords;
    private String denyMsg;
    private Random rdm = new Random();
    private List<String> replaceWords;
    private String replaceWord;

    public CatFilter() {
        setup();
    }

    public void setup() {
        badWords = ConfigManager.getInstance().getCatbotConfig().getStringList("BadWords");
        denyMsg = ConfigManager.getInstance().getCatbotConfig().getString("DenyMsg");
        replaceWords = ConfigManager.getInstance().getCatbotConfig().getStringList("ReplaceWords");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(AsyncPlayerChatEvent e) {

        String msg = e.getMessage();
        Player player = e.getPlayer();
        boolean isBad = false;

        //make sure chat is not muted, if it is don't do anything and tell the player that the chat is muted
        if (ConfigManager.getInstance().getCatbotConfig().getBoolean("ChatEnabled")) {

            //chat is enabled, so we need to filter out bad words
            for (String bad : badWords) {

                //see if the message contains a bad word that we need to filter
                if (msg.toLowerCase().matches("(?iu).*" + bad + ".*")) {
                    replaceWord = replaceWords.get(rdm.nextInt(replaceWords.size()));
                    msg = msg.replaceAll("(?iu)" + bad, replaceWord);
                    isBad = true;
                }

            }

            e.setMessage(msg);

            if (isBad) {
                MessageManager.getInstance().sendPrefixMessage(PrefixType.CATBOT, denyMsg, player);
            }


        } else {
            MessageManager.getInstance().sendPrefixMessage(PrefixType.CATBOT, " Global chat is currently disabled", player);
        }

    }

}
