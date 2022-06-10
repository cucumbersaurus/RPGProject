package project.rpg

import io.github.monun.kommand.kommand
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import project.rpg.commands.*
import project.rpg.listeners.*
import project.rpg.manager.FileManager
import project.rpg.manager.ItemManager
import project.rpg.player.PlayerInformation
import project.rpg.player.info.Mana
import project.rpg.skill.SkillDic
import project.rpg.ui.ActionBarUI
import java.util.*

class Rpg : JavaPlugin() {

    @JvmField
    var actionBar = ActionBarUI(this)
    var mana = Mana(this)

    override fun onEnable() {
        logger.info("RPG plugin loading!")

        registerEvents()
        registerCommands

        loadObjects()
        ItemManager.makeItems()

        checkOnlinePlayers()

        mana.startManaRefilling()
        actionBar.startActionBar()
        SkillDic.addAll()

        logger.info("RPG plugin loaded!")
    }

    override fun onDisable() {
        saveObjects()
        logger.info("RPG plugin disabled")
    }

    private val registerCommands: Unit
        get() {
            Objects.requireNonNull(getCommand("quests"))!!.setExecutor(QuestToggleCommand())
            Objects.requireNonNull(getCommand("savef"))!!.setExecutor(FileSaveTestCommand())
            Objects.requireNonNull(getCommand("status")!!).setExecutor(StatusCommand())
            Objects.requireNonNull(getCommand("titleTest"))!!.setExecutor(TitleTestCommand())
            Objects.requireNonNull(getCommand("testItem"))!!.setExecutor(TestItemCommand())
            Objects.requireNonNull(getCommand("skill"))!!.setExecutor(SkillCommand())
            kommand{
                TestCommand.register(this, this@Rpg)
            }
        }

    private fun registerEvents() {
        server.pluginManager.registerEvents(BlockClickEventListener(), this)
        server.pluginManager.registerEvents(EntityTakeDamageEventListener(this), this)
        server.pluginManager.registerEvents(InventoryEventListener(), this)
        server.pluginManager.registerEvents(PlayerItemUseEventListener(this), this)
        server.pluginManager.registerEvents(PlayerJoinEventListener(), this)
        server.pluginManager.registerEvents(PlayerLevelUpListener(), this)
        server.pluginManager.registerEvents(PlayerQuitEventListener(), this)
        server.pluginManager.registerEvents(PlayerRespawnEventListener(this), this)
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