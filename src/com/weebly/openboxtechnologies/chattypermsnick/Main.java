package com.weebly.openboxtechnologies.chattypermsnick;

import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.*;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.*;
import java.util.HashMap;

public class Main extends Plugin {

    static HashMap<String, ChatColor> prefixes = new HashMap<>();
    static Configuration config;

    @Override
    public void onEnable() {
        createConfig();
        for (String t : config.getKeys()) {
            switch (config.getString(t)) {
                case "BLACK":
                    prefixes.put(t, ChatColor.BLACK);
                    break;
                case "DARK_BLUE":
                    prefixes.put(t, ChatColor.DARK_BLUE);
                    break;
                case "DARK_GREEN":
                    prefixes.put(t, ChatColor.DARK_GREEN);
                    break;
                case "DARK_AQUA":
                    prefixes.put(t, ChatColor.DARK_AQUA);
                    break;
                case "DARK_RED":
                    prefixes.put(t, ChatColor.DARK_RED);
                    break;
                case "DARK_PURPLE":
                    prefixes.put(t, ChatColor.DARK_PURPLE);
                    break;
                case "GOLD":
                    prefixes.put(t, ChatColor.GOLD);
                    break;
                case "GRAY":
                    prefixes.put(t, ChatColor.GRAY);
                    break;
                case "DARK_GRAY":
                    prefixes.put(t, ChatColor.DARK_GRAY);
                    break;
                case "BLUE":
                    prefixes.put(t, ChatColor.BLUE);
                    break;
                case "GREEN":
                    prefixes.put(t, ChatColor.GREEN);
                    break;
                case "AQUA":
                    prefixes.put(t, ChatColor.AQUA);
                    break;
                case "RED":
                    prefixes.put(t, ChatColor.RED);
                    break;
                case "LIGHT_PURPLE":
                    prefixes.put(t, ChatColor.LIGHT_PURPLE);
                    break;
                case "YELLOW":
                    prefixes.put(t, ChatColor.YELLOW);
                    break;
                case "WHITE":
                    prefixes.put(t, ChatColor.WHITE);
                    break;
                case "MAGIC":
                    prefixes.put(t, ChatColor.MAGIC);
                    break;
                case "BOLD":
                    prefixes.put(t, ChatColor.BOLD);
                    break;
                case "STRIKETHROUGH":
                    prefixes.put(t, ChatColor.STRIKETHROUGH);
                    break;
                case "UNDERLINE":
                    prefixes.put(t, ChatColor.UNDERLINE);
                    break;
                case "ITALIC":
                    prefixes.put(t, ChatColor.ITALIC);
                    break;
                case "RESET":
                    prefixes.put(t, ChatColor.RESET);
                    break;
                default:
                    getLogger().warning("UUID: " + t + " has an invalid color! Ignoring ...");
                    break;
            }
        }
        getProxy().getPluginManager().registerCommand(this, new PrefixCommand());
        getProxy().getPluginManager().registerCommand(this, new NickCommand());
        getProxy().getPluginManager().registerListener(this, new EventsListener());
    }

    @Override
    public void onDisable() {
        for (String t : prefixes.keySet()) {
            config.set(t, prefixes.get(t).toString());
        }
    }

    private void createConfig() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                try (InputStream is = getResourceAsStream("config.yml");
                     OutputStream os = new FileOutputStream(configFile)) {
                    ByteStreams.copy(is, os);
                }
            } catch (IOException e) {
                throw new RuntimeException("Unable to create configuration file", e);
            }
        }
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
        } catch (IOException e) {
            throw new RuntimeException("Unable to create configuration file", e);
        }
    }

}
