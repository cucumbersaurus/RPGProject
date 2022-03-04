package rpgProject.rpg;

import org.bukkit.plugin.java.JavaPlugin;

public final class Rpg extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("RPG plugin loading!");
        getLogger().info("RPG plugin loaded!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("RPG plugin disabled");
    }
}
