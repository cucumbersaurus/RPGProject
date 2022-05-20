package project.rpg;

import org.bukkit.plugin.java.JavaPlugin;
import project.rpg.commands.QuestToggleCommand;
import project.rpg.commands.StatusCommand;
import project.rpg.commands.TestCommand;
import project.rpg.commands.TitleTestCommand;
import project.rpg.listeners.BlockClickEventListener;
import project.rpg.listeners.InventoryEventListener;
import project.rpg.listeners.PlayerJoinEventListener;
import project.rpg.listeners.PlayerQuitEventListener;
import project.rpg.manager.FileManager;
import project.rpg.ui.ActionBarUI;

import java.util.Objects;

public final class Rpg extends JavaPlugin {

    @Override
    public void onEnable() {

        getLogger().info("RPG plugin loading!");

        registerEvents();
        getCommands();
        mkObjects();
        ActionBarUI actionBar = new ActionBarUI(this);
        actionBar.startActionBar();

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
        Objects.requireNonNull(getCommand("status")).setExecutor(new StatusCommand());
        Objects.requireNonNull(getCommand("test")).setExecutor(new TestCommand());
        Objects.requireNonNull(getCommand("titleTest")).setExecutor(new TitleTestCommand());
    }

    private void registerEvents(){
        getServer().getPluginManager().registerEvents(new PlayerJoinEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitEventListener(), this);
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
    /*
    private void setPlugin(){
        ActionBarUI.setPlugin(this);
    }
    */

}
