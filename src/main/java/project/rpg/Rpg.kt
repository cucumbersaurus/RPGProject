package project.rpg

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import project.rpg.commands.*
import project.rpg.commands.test.TitleTestCommand
import project.rpg.events.listeners.*
import project.rpg.manager.FileManager
import project.rpg.player.PlayerInformation
import project.rpg.player.mana.Mana
import project.rpg.ui.ActionBarUI

class Rpg : JavaPlugin() {

    @JvmField
    val actionBar = ActionBarUI(this)

    override fun onLoad(){
        loadData()
        Initializer.initializeAll()
    }

    override fun onEnable() {
        logger.info("RPG plugin loading!")
        checkOnlinePlayers()

        registerEvents()
        registerCommands()
        registerTabCompleter()

        actionBar.startActionBar()
        Mana.startManaRefilling(this)

    }

    override fun onDisable() {
        saveData()
        logger.info("saving files...")
        //Thread.sleep(1000)
        logger.info("RPG plugin disabled")
    }

    private fun registerCommands(){
        getCommand("quests")!!.setExecutor(QuestToggleCommand())
        getCommand("saveFile")!!.setExecutor(FileSaveCommand())
        getCommand("status")!!.setExecutor(StatusCommand())
        getCommand("titleTest")!!.setExecutor(TitleTestCommand())
        getCommand("getItem")!!.setExecutor(ItemCommand())
        getCommand("skill")!!.setExecutor(SkillCommand())
        getCommand("menu")!!.setExecutor(MainMenuCommand())
        getCommand("level")!!.setExecutor(LevelCommand())
        getCommand("craft")!!.setExecutor(CraftCommand())
        getCommand("friend")!!.setExecutor(FriendCommand())
        /*kommand {
            TestCommand.register(this, this@Rpg)
        }*/
    }

    private fun registerTabCompleter(){
        getCommand("quests")!!.tabCompleter = QuestToggleCommand()
        getCommand("skill")!!.tabCompleter = SkillCommand()
        getCommand("status")!!.tabCompleter = StatusCommand()
        getCommand("friend")!!.tabCompleter = FriendCommand()
    }

    private fun registerEvents() {
        server.pluginManager.registerEvents(BlockClickEventListener(), this)
        server.pluginManager.registerEvents(EntityTakeDamageEventListener(this), this)
        server.pluginManager.registerEvents(InventoryEventListener(), this)
        server.pluginManager.registerEvents(PlayerItemUseEventListener(this), this)
        server.pluginManager.registerEvents(PlayerJoinEventListener(), this)
        server.pluginManager.registerEvents(PlayerQuitEventListener(), this)
        server.pluginManager.registerEvents(RespawnEventListener(this), this)
        server.pluginManager.registerEvents(PlayerItemConsumeEventListener(this), this)
    }

    private fun loadData() {
        FileManager.makeDir()
        //FileManager.makeFile()
        //FileManager.getFile()
    }

    private fun saveData() {
        //FileManager.makeFile()
        //FileManager.saveFile()
        FileManager.makeDir()
        FileManager.syncedSaveAll()
    }

    private fun checkOnlinePlayers() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, {
            for (player in Bukkit.getOnlinePlayers()) {
                PlayerInformation.makeInfo(player)
                //FileManager.getFile(player)
            }
            logger.info("RPG plugin loading completed")
        }, 10)
    }
}