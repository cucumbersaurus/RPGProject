package project.rpg

import io.github.monun.heartbeat.coroutines.HeartbeatScope
import io.github.monun.kommand.kommand
import kotlinx.coroutines.launch
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin
import project.rpg.commands.*
import project.rpg.commands.debug.*
import project.rpg.database.Database
import project.rpg.events.listeners.*
import project.rpg.events.listeners.debug.CustomMobAttackEventListener
import project.rpg.player.PlayerInformation
import project.rpg.player.mana.Mana
import project.rpg.ui.text.ActionBarUI


class Rpg : JavaPlugin() {

    init{
        actionBar = ActionBarUI()
        plugin = this
    }

    override fun onLoad() {
        Initializer.initializeAll()

        Database.run {
            connect()
            createTables()
        }
    }

    override fun onEnable() {
        logger.info("RPG plugin loading!")
        checkOnlinePlayers()

        registerEvents()
        registerCommands()
        registerTabCompleter()

        //actionBar.startActionBar()
        actionBar.startCoroutineActionBar()
        Mana.startManaRefilling()

    }

    override fun onDisable() {
        logger.info("saving files...")

        Bukkit.getOnlinePlayers().forEach {
            Database.writeUser(it)
        }

        logger.info("RPG plugin disabled")
    }

    private fun registerCommands() {
        getCommand("quests")!!.setExecutor(QuestToggleCommand())
        //getCommand("saveFile")!!.setExecutor(FileSaveCommand())
        getCommand("status")!!.setExecutor(StatusCommand())
        getCommand("titleTest")!!.setExecutor(TitleTestCommand())
        getCommand("getItem")!!.setExecutor(ItemCommand())
        getCommand("menu")!!.setExecutor(MainMenuCommand())
        getCommand("level")!!.setExecutor(LevelCommand())
        getCommand("craft")!!.setExecutor(CraftCommand())
        getCommand("friend")!!.setExecutor(FriendCommand())
        //getCommand("mob")!!.setExecutor(MobTest())
        getCommand("insertsql")!!.setExecutor(SQLInsertCommand())
        getCommand("serialize")!!.setExecutor(SerializeCommand())

        kommand {

            //debug/test commands
            registerTestCommand()
            registerSampleKommand()
            registerMobCommand()

        }
    }

    private fun registerTabCompleter() {
        getCommand("quests")!!.tabCompleter = QuestToggleCommand()
        getCommand("status")!!.tabCompleter = StatusCommand()
        getCommand("friend")!!.tabCompleter = FriendCommand()
    }

    private fun registerEvents() {
        with(server.pluginManager) {
            registerEvents(BlockClickEventListener(), plugin)
            registerEvents(EntityTakeDamageEventListener(), plugin)
            registerEvents(InventoryEventListener(), plugin)
            registerEvents(PlayerItemUseEventListener(), plugin)
            registerEvents(PlayerJoinEventListener(), plugin)
            registerEvents(PlayerQuitEventListener(), plugin)
            registerEvents(RespawnEventListener(), plugin)
            registerEvents(PlayerItemConsumeEventListener(plugin as Rpg), plugin)
            registerEvents(ProjectileEventListener(), plugin)

            //debugging/testing events
            registerEvents(CustomMobAttackEventListener(), plugin)
        }
    }

    private fun checkOnlinePlayers() {
        HeartbeatScope().launch {
            Bukkit.getOnlinePlayers().forEach {
                PlayerInformation.makeInfo(it)
            }
            logger.info("RPG plugin loading completed")
        }
    }

    companion object{
        lateinit var actionBar: ActionBarUI
        lateinit var plugin: JavaPlugin

        fun pluginNamespacedKey(key: String):NamespacedKey = NamespacedKey(plugin, key)
    }
}