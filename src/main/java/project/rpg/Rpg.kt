package project.rpg

import io.github.monun.heartbeat.coroutines.HeartbeatScope
import kotlinx.coroutines.launch
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.plugin.java.JavaPlugin
import project.rpg.commands.*
import project.rpg.commands.test.MobTest
import project.rpg.commands.test.TitleTestCommand
import project.rpg.events.listeners.*
import project.rpg.manager.FileManager
import project.rpg.player.PlayerInformation
import project.rpg.player.mana.Mana
import project.rpg.ui.text.ActionBarUI

class Rpg : JavaPlugin() {

    @JvmField
    val actionBar = ActionBarUI(this)

    override fun onLoad(){
        loadData()
        Initializer.initializeAll()
    }

    override fun onEnable() {
        logger.info("RPG plugin loading!" + ChatColor.AQUA)
        checkOnlinePlayers()

        registerEvents()
        registerCommands()
        registerTabCompleter()

        //actionBar.startActionBar()
        actionBar.startCoroutineActionBar()
        Mana.startManaRefilling()

    }

    override fun onDisable() {
        saveData()
        logger.info("saving files..." + ChatColor.YELLOW)
        //Thread.sleep(1000)
        logger.info("RPG plugin disabled" + ChatColor.AQUA)
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
        getCommand("mob")!!.setExecutor(MobTest())
        /*
        kommand {
            //TestCommand.register(this, this@Rpg)
            register("user"){
                executes {
                    sender.sendMessage("test")
                }
            }
        }
        */
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
        server.pluginManager.registerEvents(ProjectileEventListener(this), this)
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
        HeartbeatScope().launch {
            for (player in Bukkit.getOnlinePlayers()) {
                PlayerInformation.makeInfo(player)
                //FileManager.getFile(player)
            }
            logger.info("RPG plugin loading completed")
        }
    }
}