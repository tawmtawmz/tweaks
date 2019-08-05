package com.tawmtawmz.tweaks;

public class constants {
    public static final String COMMAND_MAIN = "tt";

    public static final String COMMAND_DROP = "drop";
    public static final String COMMAND_SLEEP = "sleep";
    public static final String COMMAND_SPAWN = "spawn";
    public static final String COMMAND_SHULKER = "shulker";

    public static final String SUBCOMMAND_ENABLE = "enable";
    public static final String SUBCOMMAND_DISABLE = "disable";
    public static final String SUBCOMMAND_ADD = "add";
    public static final String SUBCOMMAND_REMOVE = "remove";
    public static final String SUBCOMMAND_MULTIPLY = "multiply";
    public static final String SUBCOMMAND_LIST = "list";
    public static final String SUBCOMMAND_CLEAR = "clear";

    public static final String KEY_DROP = "drops_enabled";
    public static final String KEY_SLEEP = "single_sleep_enabled";
    public static final String KEY_SPAWN = "set_spawn_enabled";
    public static final String KEY_SHULKER = "endermite_evolve_enabled";
    public static final String KEY_DROP_DATA = "drops";

    // ticks (20 a second) * seconds * minutes
    public static final long ENDERMITE_EVOLVE_TIME = 20L * 60L * 2L;
    public static final long PLAYER_SLEEP_TIME = 20L;
}
