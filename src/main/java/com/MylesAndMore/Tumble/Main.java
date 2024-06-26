package com.MylesAndMore.Tumble;

import com.MylesAndMore.Tumble.commands.*;
import com.MylesAndMore.Tumble.config.ArenaManager;

import com.MylesAndMore.Tumble.config.ConfigManager;
import com.MylesAndMore.Tumble.config.LanguageManager;
import org.bstats.bukkit.Metrics;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin{
    public static Main plugin;

    public static LanguageManager languageManager;
    public static ArenaManager arenaManager;
    public static ConfigManager configManager;

    @Override
    public void onEnable() {
        plugin = this;

        languageManager = new LanguageManager();
        arenaManager = new ArenaManager();
        configManager = new ConfigManager();

        Objects.requireNonNull(this.getCommand("tumble")).setExecutor(new Tumble());
        new Metrics(this, 16940);

        Bukkit.getServer().getLogger().info("[Tumble] Tumble successfully enabled!");
    }
}