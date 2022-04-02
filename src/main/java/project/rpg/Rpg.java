package project.rpg;

import org.bukkit.plugin.java.JavaPlugin;
import project.rpg.commands.CreateTmpStatus;
import project.rpg.commands.QuestToggle;
import project.rpg.commands.Save;
import project.rpg.file.FileSystem;
import project.rpg.listeners.BlockClickEvent;
import project.rpg.listeners.InventoryEventListener;

import java.util.Objects;

public final class Rpg extends JavaPlugin {

    @Override
    public void onEnable() {

        getLogger().info("RPG plugin loading!");

        registerEvents();
        getCommands();
        FileSystem.mkFile();

        getLogger().info("RPG plugin loaded!");
    }

    @Override
    public void onDisable() {

        getLogger().info("RPG plugin disabled");
    }

    private void getCommands(){
        Objects.requireNonNull(getCommand("quests")).setExecutor(new QuestToggle());
        Objects.requireNonNull(getCommand("savef")).setExecutor(new Save());
        Objects.requireNonNull(getCommand("status")).setExecutor(new CreateTmpStatus());
    }

    private void registerEvents(){
        getServer().getPluginManager().registerEvents(new InventoryEventListener(), this);
        getServer().getPluginManager().registerEvents(new BlockClickEvent(), this);
    }
}
