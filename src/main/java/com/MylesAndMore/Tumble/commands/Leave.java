package com.MylesAndMore.Tumble.commands;

import com.MylesAndMore.Tumble.game.Game;
import com.MylesAndMore.Tumble.plugin.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Leave implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This cannot be run by the console");
            return false;
        }

        if (!sender.hasPermission("tumble.leave")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to perform this command!");
            return false;
        }

        Game game = ConfigManager.findGamePlayerIsIn((Player)sender);
        if (game == null) {
            sender.sendMessage(ChatColor.RED + "You are not in a game.");
            return false;
        }

        game.removePlayer((Player) sender);
        sender.sendMessage(ChatColor.GREEN + "Game left.");
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        return new ArrayList<>();
    }
}