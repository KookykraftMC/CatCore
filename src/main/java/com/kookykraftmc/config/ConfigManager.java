package com.kookykraftmc.config;

import com.kookykraftmc.CatCore;
import com.kookykraftmc.utils.LogUtil;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.*;


public class ConfigManager {

    private static ConfigManager instance = new ConfigManager();
    private static Plugin p = CatCore.getPlugin();

    private FileConfiguration messages;
    private FileConfiguration lobby;

    private File messagesFile;
    private File lobbyFile;

    private static final int CONFIG_VERSION = 0;
    private static final int MESSAGES_VERSION = 0;
    private static final int LOBBY_VERSION = 0;


    public ConfigManager() {

    }

    public static ConfigManager getInstance() {
        return instance;
    }

    public void setup() {

        //load the default config
        p.getConfig().options().copyDefaults(true);
        p.saveDefaultConfig();

        //load our config files
        messagesFile = new File(p.getDataFolder(), "messages.yml");
        lobbyFile = new File(p.getDataFolder(), "lobby.yml");

        try {
            if(!messagesFile.exists()) {
                loadFile("messages.yml");
            }

            if (!lobbyFile.exists()) {
                loadFile("lobby.yml");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        //load our config files
        messages = YamlConfiguration.loadConfiguration(messagesFile);
        lobby = YamlConfiguration.loadConfiguration(lobbyFile);

    }


    public FileConfiguration getConfig() {
        return p.getConfig();
    }

    public FileConfiguration getMessagesConfig() {
        return messages;
    }

    public FileConfiguration getLobbyConfig() {
        return lobby;
    }

    public boolean isDebugEnabled() {
        return p.getConfig().getBoolean("debug");
    }

    public void loadFile(String file)
    {
        File t = new File(p.getDataFolder(), file);
        LogUtil.LogToConsole("Writing new file: " + t.getAbsolutePath());

        try {
            t.createNewFile();
            FileWriter out = new FileWriter(t);
            if (!isDebugEnabled()) {
                //should we print the file we are writing for debug purposes?
                System.out.println(file);
            }
            //InputStream is = getClass().getResourceAsStream("/"+file);
            InputStream is = p.getResource(file);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                out.write(line + "\n");
                if (!isDebugEnabled()) {
                    System.out.print(line);
                }
            }
            out.flush();
            is.close();
            isr.close();
            br.close();
            out.close();

            if (!isDebugEnabled()) {
                LogUtil.LogToConsole("Loaded Config " + file + " successfully!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean moveFile(File oldConfig) {
        LogUtil.LogToConsole("Moving outdated config file: " + oldConfig.getName());
        String name = oldConfig.getName();
        File configBackup = new File(p.getDataFolder(), getNextName(name, 0));
        return oldConfig.renameTo(configBackup);
    }

    public String getNextName(String name, int n){
        File oldConfig = new File(p.getDataFolder(), name+".old"+n);
        if(!oldConfig.exists()){
            return oldConfig.getName();
        }
        else{
            return getNextName(name, n+1);
        }
    }

    public void saveConfig() {
        p.saveConfig();
    }


    public void saveMessagesConfig() {
        try {

            //save our messages config file
            messages.save(messagesFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveLobbyConfig() {
        try {

            //save our lobby config file
            lobby.save(lobbyFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadConfig() {
        p.reloadConfig();
    }

    public void reloadMessagesConfig() {
        messages = YamlConfiguration.loadConfiguration(messagesFile);
        if (messages.getInt("version") != MESSAGES_VERSION ) {
            moveFile(messagesFile);
            loadFile("messages.yml");
        }
        messages.set("version", MESSAGES_VERSION);
        saveMessagesConfig();
    }

    public void reloadLobbyConfig() {
        lobby = YamlConfiguration.loadConfiguration(lobbyFile);
        if (lobby.getInt("version") != LOBBY_VERSION) {
            moveFile(lobbyFile);
            reloadLobbyConfig();
        }
        lobby.set("version", LOBBY_VERSION);
        saveLobbyConfig();
    }

}
