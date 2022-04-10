package project.rpg;

import org.bukkit.plugin.java.JavaPlugin;
import project.rpg.commands.QuestToggleCommand;
import project.rpg.commands.StatusCommand;
import project.rpg.listeners.BlockClickEventListener;
import project.rpg.listeners.InventoryEventListener;
import project.rpg.manager.FileManager;

import java.util.Objects;

public final class Rpg extends JavaPlugin {

    @Override
    public void onEnable() {

        getLogger().info("RPG plugin loading!");

        registerEvents();
        getCommands();
        mkObjects();
        //ArrayManager.putJson();

        getLogger().info("RPG plugin loaded!");
    }

    @Override
    public void onDisable() {

        saveObjects();
        getLogger().info("RPG plugin disabled");
    }

    private void getCommands(){
        Objects.requireNonNull(getCommand("quests")).setExecutor(new QuestToggleCommand());
        Objects.requireNonNull(getCommand("stats")).setExecutor(new StatusCommand());
    }

    private void registerEvents(){
        getServer().getPluginManager().registerEvents(new InventoryEventListener(), this);
        getServer().getPluginManager().registerEvents(new BlockClickEventListener(), this);
    }

    private void mkObjects(){
        FileManager.makeFile();
        FileManager.makeList();
    }

    private void saveObjects(){
        mkObjects();
        FileManager.saveList();
        FileManager.saveFile();
    }
}
