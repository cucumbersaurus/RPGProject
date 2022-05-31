package project.rpg;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import project.rpg.commands.*;
import project.rpg.listeners.*;
import project.rpg.manager.FileManager;
import project.rpg.player.PlayerInformation;
import project.rpg.ui.ActionBarUI;

import java.util.Objects;

public final class Rpg extends JavaPlugin {
    public ActionBarUI actionBar = new ActionBarUI(this);

    @Override
    public void onEnable() {

        getLogger().info("RPG plugin loading!");

        registerEvents();
        getCommands();
        loadObjects();

        checkOnlinePlayers();
        actionBar.startActionBar();

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
        Objects.requireNonNull(getCommand("savef")).setExecutor(new FileSaveTestCommand());
        Objects.requireNonNull(getCommand("skill")).setExecutor(new SkillCommand());
    }

    private void registerEvents(){
        getServer().getPluginManager().registerEvents(new PlayerJoinEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitEventListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryEventListener(), this);
        getServer().getPluginManager().registerEvents(new BlockClickEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawnEventListener(this), this);
        getServer().getPluginManager().registerEvents(new EntityTakeDamageEvent(this), this);
        getServer().getPluginManager().registerEvents(new PlayerRightClickListener(), this);
    }

    private void loadObjects() {
        FileManager.makeFile();
        FileManager.getFile();
    }

    private void saveObjects(){
        FileManager.makeFile();
        FileManager.saveFile();
    }

    private void checkOnlinePlayers(){
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, ()->{
            for(Player player : Bukkit.getOnlinePlayers()){
                PlayerInformation.makeInfo(player);
            }
        }, 0);
    }
}
