package com.tawmtawmz.tweaks;

import org.bukkit.plugin.java.JavaPlugin;



public class main extends JavaPlugin {
    @Override
    public void onEnable() {
        // Save a copy of the default config.yml if one is not there
        saveDefaultConfig();

        // Register "tawmtawmz" commands.
        getCommand(constants.COMMAND_MAIN).setExecutor(new commands());

        // Register events.
        getServer().getPluginManager().registerEvents(new listener(), this);
    }
}
