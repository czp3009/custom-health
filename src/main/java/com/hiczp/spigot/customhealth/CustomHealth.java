package com.hiczp.spigot.customhealth;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class CustomHealth extends JavaPlugin {
    private Logger log;

    @Override
    public void onEnable() {
        log = getLogger();

        //noinspection ConstantConditions
        getCommand("customhealth").setExecutor(new CustomHealthCommand());

        log.info("Enabled");
    }

    @Override
    public void onDisable() {
        log.info("Disabled");
    }
}
