package com.MylesAndMore.tumble.commands;

import com.MylesAndMore.tumble.TumbleManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class StartGame implements CommandExecutor {
    // Define the startGame method so that other classes can refrence it
    public void startGame(CommandSender sender, String[] args) {
        // Check if sender has perms to run command
        if (sender.hasPermission("tumble.start")) {
            // Check if there is a lobbyWorld specified in config
            if (TumbleManager.getLobbyWorld() != null) {
                // Check if there is more than one person in lobby
                if (TumbleManager.getPlayersInLobby().size() > 1) {
                    // Check if there is a gameWorld specified in config
                    if (TumbleManager.getGameWorld() != null) {
                        sender.sendMessage("Checking world, this could take a few moments...");
                        // Use multiverse to load game world
                        // If the load was successful, start game
                        if (TumbleManager.getMVWorldManager().loadWorld(TumbleManager.getGameWorld())) {
                            sender.sendMessage("Starting game, please wait.");

                            // Generate the blocks in game world


                            // While there are still players in the lobby, send them to the gameWorld
                            // This is just a way of sending everybody in the lobby to the game
                            for (List<Player> playersInLobby = TumbleManager.getPlayersInLobby(); playersInLobby.size() > 0; playersInLobby = TumbleManager.getPlayersInLobby()) {
                                // Get a singular player from the player list
                                Player aPlayer = playersInLobby.get(0);
                                // Teleport that player to the spawn of the gameWorld
                                aPlayer.teleport(Bukkit.getWorld(TumbleManager.getGameWorld()).getSpawnLocation());
                            }

                            // Give players game item (shovels/snowballs/etc.)

                            // Add a little break because it can take the clients a bit to load into the new world
                            // Then, transition to another method because this one is getting really long
                        }
                        // If load was unsuccessful, give feedback
                        // Note: this should not occur unless the config file was edited externally,
                        // because the plugin prevents adding "worlds" that are not actually present to the config.
                        else {
                            sender.sendMessage(ChatColor.RED + "Failed to find a world named " + ChatColor.GRAY + TumbleManager.getGameWorld());
                            sender.sendMessage(ChatColor.RED + "Is the configuration file correct?");
                        }
                    }
                    // Feedback for if there is no gameWorld in the config
                    else {
                        sender.sendMessage(ChatColor.RED + "Please link a game world first!");
                    }
                }
                // Feedback for if there is only one person online
                else {
                    sender.sendMessage(ChatColor.RED + "You can't start a game with yourself!");
                }
            }
            else {
                sender.sendMessage(ChatColor.RED + "Please link a lobby world first!");
            }
        }
        // Feedback for if the sender has no perms
        else {
            sender.sendMessage(ChatColor.RED + TumbleManager.getPermissionMessage());
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        startGame(sender, args);
        return true;
    }
}
