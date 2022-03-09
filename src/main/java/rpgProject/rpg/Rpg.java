package rpgProject.rpg;

import org.bukkit.plugin.java.JavaPlugin;
import rpgProject.commands.QuestToggle;
import rpgProject.eventListeners.BlockClickEvent;
import rpgProject.eventListeners.InventoryEventListener;

import java.util.Objects;

public final class Rpg extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("RPG plugin loading!");
        getLogger().info("RPG plugin loaded!");

        registerEvents();
        getCommands();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("RPG plugin disabled");
    }

    private void getCommands(){
        Objects.requireNonNull(getCommand("quests")).setExecutor(new QuestToggle());
    }

    private void registerEvents(){
        getServer().getPluginManager().registerEvents(new InventoryEventListener(), this);
        getServer().getPluginManager().registerEvents(new BlockClickEvent(), this);
    }

}
