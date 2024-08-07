package com.MylesAndMore.Tumble.commands;

import com.MylesAndMore.Tumble.config.ArenaManager;
import com.MylesAndMore.Tumble.config.LanguageManager;
import com.MylesAndMore.Tumble.game.Game;
import com.MylesAndMore.Tumble.plugin.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ForceStop implements SubCommand, CommandExecutor, TabCompleter {

    @Override
    public String getCommandName() {
        return "forcestop";
    }

    @Override
    public String getPermission() {
        return "tumble.forcestop";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        Game game;
        if (args.length < 1 || args[0] == null) {
            // No arena passed in, try to infer from game player is in
            game = ArenaManager.findGamePlayerIsIn((Player)sender);
            if (game == null) {
                sender.sendMessage(LanguageManager.fromKey("missing-arena-parameter"));
                return false;
            }
        } else {
            String arenaName = args[0];
            if (!ArenaManager.arenas.containsKey(arenaName)) {
                sender.sendMessage(LanguageManager.fromKey("invalid-arena").replace("%arena%",arenaName));
                return false;
            }
            game = ArenaManager.arenas.get(arenaName).game;
        }

        if (game == null) {
            sender.sendMessage(LanguageManager.fromKey("no-game-in-arena"));
            return false;
        }

        game.stopGame();
        sender.sendMessage(LanguageManager.fromKey("forcestop-success"));
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length == 1) {
            return ArenaManager.arenas.keySet().stream().toList();
        }
        return new ArrayList<>();
    }
}
