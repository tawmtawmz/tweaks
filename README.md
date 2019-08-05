## tawmtawmz tweaks

A collection of Minecraft tweaks implemented as a [Paper](https://papermc.io/) plugin to allow for a better 
survival multi-player experience while still keeping a Vanilla Minecraft feel. The idea for most of these tweaks 
originally comes from data packs (notably [Xisumavoid's](https://vanillatweaks.net/picker/datapacks/)). They have been 
converted to plugin form to hopefully alleviate data pack lag.

This plugin was originally created for the [Avalonia Minecraft Server](avalonia.serverminer.com) to run on Minecraft 
1.14.4, but can be used on any compatible Paper-MC server.

#### Features
- Custom Drop Tables
- Farmable Shulker Boxes
- Single-player Sleep
- Daytime Bed Spawn

#### Commands

- `/tt drop` - add/remove/multiply any item to any drop table
- `/tt sleep` - enable/disable single-player sleep
- `/tt spawn` - enable/disable daytime bed spawn point setting
- `/tt shulker` - enable/disable Endermite burrowing into Chrous Fruits to evolve into Shulkers

#### How To

The general syntax is `/tt <command> <subcommand> [<entity>] [<item>] [<rate>] [<quantity>]`. All tweaks are 
enabled by default but can enabled/disabled manually with `/tt <command> enable` or `/tt <command> disable`. The
configuration is loaded by the `config.yml` file within the plugin's JAR file and are persisted across server reboots.
Making a change with a command will update the configuration file.

Here are few examples:
- `/tt drop enable` - enable custom drop tables (enabled by default)
- `/tt drop add ender_dragon elytra 0.5 1` - the Ender Dragon will drop 1 Elytra 50% of time
- `/tt drop list ender_dragon` - show custom drop tables for the Ender Dragon
- `/tt drop clear ender_dragon` - reset custom drop tables for the Ender Dragon
- `/tt drop add zombie diamond 1.0 3` - zombies will drop 3 diamonds 100% of the time
- `/tt drop add zombie diamond 0.5 1` x 3 - zombies will drop anywhere from 0 to 3 diamonds (yes, commands stack until clearing)
- `/tt drop add zombie zombie_head 0.01 1` - zombies will drop their head 1% of the time (does not overwrite other drops)
- `/tt drop remove zombie rotten_flesh` - zombies will no longer drop rotten flesh
- `/tt drop multiply shulker shulker_shell 2.0` - multiply Shulker Shell drops by 200% (they should drop double)

The full list of entities can be found [here](https://papermc.io/javadocs/paper/1.14/org/bukkit/entity/EntityType.html). 
The full list of items can be found [here](https://papermc.io/javadocs/paper/1.14/org/bukkit/Material.html). Just use 
the lower case name with underscores for each.

#### Compiling

Load the project in IntelliJ with Maven and double click "package" under the "Lifecycle" Maven Projects Window.

#### Future Development

Currently I have no plans for continued development for this plugin. If this plugin gains interest, that may change.
