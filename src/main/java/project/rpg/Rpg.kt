package project.rpg

import io.github.monun.kommand.kommand
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import project.rpg.commands.*
import project.rpg.events.listeners.*
import project.rpg.manager.FileManager
import project.rpg.manager.ItemManager
import project.rpg.player.PlayerInformation
import project.rpg.player.info.Mana
import project.rpg.skill.SkillDic
import project.rpg.ui.ActionBarUI

class Rpg : JavaPlugin() {

    @JvmField
    val actionBar = ActionBarUI(this)
    val mana = Mana(this)

    override fun onLoad(){
        loadObjects()
        ItemManager.makeItems()

        SkillDic.addAll()
    }

    override fun onEnable() {
        logger.info("RPG plugin loading!")
        checkOnlinePlayers()

        registerEvents()
        registerCommands()
        registerTabCompleter()

        actionBar.startActionBar()
        mana.startManaRefilling()

        logger.info("RPG plugin loaded!")
    }

    override fun onDisable() {
        saveObjects()
        logger.info("RPG plugin disabled")
    }

    private fun registerCommands(){
        getCommand("quests")!!.setExecutor(QuestToggleCommand())
        getCommand("savef")!!.setExecutor(FileSaveTestCommand())
        getCommand("status")!!.setExecutor(StatusCommand())
        getCommand("titleTest")!!.setExecutor(TitleTestCommand())
        getCommand("testItem")!!.setExecutor(TestItemCommand())
        getCommand("skill")!!.setExecutor(SkillCommand())
        getCommand("menu")!!.setExecutor(MainMenuCommand())
        getCommand("level")!!.setExecutor(LevelCommand())
        getCommand("craft")!!.setExecutor(CraftCommand())
        kommand {
            TestCommand.register(this, this@Rpg)
        }
    }

    private fun registerTabCompleter(){
        getCommand("quests")!!.tabCompleter = QuestToggleCommand()
        getCommand("skill")!!.tabCompleter = SkillCommand()
        getCommand("status")!!.tabCompleter = StatusCommand()
    }

    private fun registerEvents() {
        server.pluginManager.registerEvents(BlockClickEventListener(), this)
        server.pluginManager.registerEvents(EntityTakeDamageEventListener(this), this)
        server.pluginManager.registerEvents(InventoryEventListener(), this)
        server.pluginManager.registerEvents(PlayerItemUseEventListener(this), this)
        server.pluginManager.registerEvents(PlayerJoinEventListener(), this)
        server.pluginManager.registerEvents(PlayerLevelUpListener(), this)
        server.pluginManager.registerEvents(PlayerQuitEventListener(), this)
        server.pluginManager.registerEvents(RespawnEventListener(this), this)
        server.pluginManager.registerEvents(PlayerPotionDrinkEventListener(this), this)
    }

    private fun loadObjects() {
        FileManager.makeFile()
        FileManager.getFile()
    }

    private fun saveObjects() {
        FileManager.makeFile()
        FileManager.saveFile()
    }

    private fun checkOnlinePlayers() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, {
            for (player in Bukkit.getOnlinePlayers()) {
                PlayerInformation.makeInfo(player)
            }
        }, 0)
    }
}