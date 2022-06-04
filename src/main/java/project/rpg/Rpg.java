package project.rpg;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import project.rpg.commands.*;
import project.rpg.listeners.*;
import project.rpg.manager.FileManager;
import project.rpg.manager.ItemManager;
import project.rpg.player.PlayerInformation;
import project.rpg.player.info.Mana;
import project.rpg.skill.SkillDic;
import project.rpg.ui.ActionBarUI;

import java.util.Objects;

public final class Rpg extends JavaPlugin {
    public ActionBarUI _actionBar = new ActionBarUI(this);
    public Mana _mana = new Mana(this);
    @Override
    public void onEnable() {

        getLogger().info("RPG plugin loading!");

        registerEvents();
        getCommands();
        loadObjects();

        ItemManager.makeItems();

        checkOnlinePlayers();
        _mana.startManaRefilling();
        _actionBar.startActionBar();

        SkillDic.addAll();

        getLogger().info("RPG plugin loaded!");
    }

    @Override
    public void onDisable() {

        saveObjects();
        getLogger().info("RPG plugin disabled");
    }

    private void getCommands(){
        Objects.requireNonNull(getCommand("quests")).setExecutor(new QuestToggleCommand());
        Objects.requireNonNull(getCommand("savef")).setExecutor(new FileSaveTestCommand());
        Objects.requireNonNull(getCommand("status")).setExecutor(new StatusCommand());
        Objects.requireNonNull(getCommand("test")).setExecutor(new TestCommand());
        Objects.requireNonNull(getCommand("titleTest")).setExecutor(new TitleTestCommand());
        Objects.requireNonNull(getCommand("testItem")).setExecutor(new TestItemCommand());
        Objects.requireNonNull(getCommand("skill")).setExecutor(new SkillCommand());
    }

    private void registerEvents(){
        getServer().getPluginManager().registerEvents(new BlockClickEventListener(), this);
        getServer().getPluginManager().registerEvents(new EntityTakeDamageEventListener(this), this);
        getServer().getPluginManager().registerEvents(new InventoryEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerItemUseEventListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerLevelUpListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawnEventListener(this), this);
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
