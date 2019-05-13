package com.hiczp.spigot.customhealth;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CustomHealthCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && !sender.isOp()) {
            sender.sendMessage("Permission denied");
            return true;
        }

        if (args.length != 2) {
            //noinspection SpellCheckingInspection
            sender.sendMessage("Usage: /customhealth <player> <max-health>");
            return true;
        }

        String playerName = args[0];
        Player player = Bukkit.getPlayer(playerName);
        if (player == null) {
            sender.sendMessage(String.format("Player %s not online", playerName));
            return true;
        }

        int maxHealth;
        try {
            maxHealth = Integer.valueOf(args[1]);
        } catch (NumberFormatException e) {
            sender.sendMessage(String.format("%s is not a valid number", args[1]));
            return true;
        }
        //noinspection ConstantConditions
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
        sender.sendMessage(String.format("Set maxHealth of player %s to %d", playerName, maxHealth));

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 0) {
            return Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
