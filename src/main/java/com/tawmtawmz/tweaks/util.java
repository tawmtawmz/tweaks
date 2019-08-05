package com.tawmtawmz.tweaks;


import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class util {
    public static Plugin getPlugin() {
        return JavaPlugin.getPlugin(main.class);
    }

    public static String dropTable(String entityTypeName) {
        return String.format("%s.%s", constants.KEY_DROP_DATA, entityTypeName);
    }
}
