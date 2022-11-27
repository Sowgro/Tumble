package com.MylesAndMore.tumble;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class PluginManager {
    // Tumble plugin
    public static Plugin getPlugin() {
        return Bukkit.getServer().getPluginManager().getPlugin("tumble");
    }

    // Tumble static methods
    public static String getPermissionMessage() { return PluginManager.getPlugin().getConfig().getString("permissionMessage"); }
    public static String getGameWorld() { return PluginManager.getPlugin().getConfig().getString("gameWorld"); }
    public static String getLobbyWorld() { return PluginManager.getPlugin().getConfig().getString("lobbyWorld"); }
    public static List<Player> getPlayersInGame() { return Bukkit.getServer().getWorld(PluginManager.getGameWorld()).getPlayers(); }
    public static List<Player> getPlayersInLobby() { return Bukkit.getServer().getWorld(PluginManager.getLobbyWorld()).getPlayers(); }


    // Multiverse plugin
    public static MultiverseCore getMV() { return (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core"); }
    // Multiverse worldManager
    public static MVWorldManager getMVWorldManager() { return getMV().getMVWorldManager(); }
}
