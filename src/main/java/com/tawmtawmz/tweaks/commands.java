package com.tawmtawmz.tweaks;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

// see: https://www.spigotmc.org/wiki/create-a-simple-command/
public class commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && sender.isOp()) {
            if (2 > args.length) {
                // TODO: whisper help
                return true;
            }
            String _command = args[0];
            String _subcommand = args[1];
            if (_subcommand.equals(constants.SUBCOMMAND_ENABLE) || _subcommand.equals(constants.SUBCOMMAND_DISABLE)) {
                String key = "";
                Boolean value = _subcommand.equals(constants.SUBCOMMAND_ENABLE);
                if (_command.equals(constants.COMMAND_DROP)) {
                    key = constants.KEY_DROP;
                } else if (_command.equals(constants.COMMAND_SLEEP)) {
                    key = constants.KEY_SLEEP;
                } else if (_command.equals(constants.COMMAND_SPAWN)) {
                    key = constants.KEY_SPAWN;
                } else if (_command.equals(constants.COMMAND_SHULKER)) {
                    key = constants.KEY_SHULKER;
                }
                if (!key.isEmpty()) {
                    util.getPlugin().getConfig().set(key, value);
                    util.getPlugin().saveConfig();
                }

                sender.sendMessage(String.format("%s is now set to %s", key, value));
            }
            else if (_subcommand.equals(constants.SUBCOMMAND_ADD)) {
                String type = args[2];
                String item = args[3];
                String rate = args[4];
                String quantity = args[5];

                String key = util.dropTable(type);
                String data = String.format("%s,%s,%s,%s", constants.SUBCOMMAND_ADD, item, rate, quantity);

                List<String> value = util.getPlugin().getConfig().getStringList(key);
                value.add(data);

                util.getPlugin().getConfig().set(key, value);
                util.getPlugin().saveConfig();

                sender.sendMessage(String.format("added drop for %s: %s", type, data));
            }
            else if (_subcommand.equals(constants.SUBCOMMAND_REMOVE)) {
                String type = args[2];
                String item = args[3];

                String key = util.dropTable(type);
                String data = String.format("%s,%s,-,-", constants.SUBCOMMAND_REMOVE, item);

                List<String> value = util.getPlugin().getConfig().getStringList(key);
                value.add(data);

                util.getPlugin().getConfig().set(key, value);
                util.getPlugin().saveConfig();

                sender.sendMessage(String.format("removed drop for %s: %s", type, data));
            }
            else if (_subcommand.equals(constants.SUBCOMMAND_MULTIPLY)) {
                String type = args[2];
                String item = args[3];
                String rate = args[4];

                String key = util.dropTable(type);
                String data = String.format("%s,%s,%s,-", constants.SUBCOMMAND_MULTIPLY, item, rate);

                List<String> value = util.getPlugin().getConfig().getStringList(key);
                value.add(data);

                util.getPlugin().getConfig().set(key, value);
                util.getPlugin().saveConfig();

                sender.sendMessage(String.format("multiplied drop for %s: %s", type, data));
            }
            else if (_subcommand.equals(constants.SUBCOMMAND_CLEAR)) {
                String type = args[2];
                String key = util.dropTable(type);

                util.getPlugin().getConfig().set(key, null);

                sender.sendMessage(String.format("cleared custom drop table for %s", type));
            }
            else if (_subcommand.equals(constants.SUBCOMMAND_LIST)) {
                String type = args[2];
                String key = util.dropTable(type);

                sender.sendMessage(String.format("custom drop table for %s:", type));
                sender.sendMessage("modifier,item,rate,quantity");
                for (String drop : util.getPlugin().getConfig().getStringList(key)) {
                    sender.sendMessage(drop);
                }
            }
        }
        // If the player (or console) uses our command correct, we can return true
        return true;
    }
}
