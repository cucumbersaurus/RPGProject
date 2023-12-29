package project.rpg.database

import org.bukkit.entity.Player
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import project.rpg.database.tables.UserData

object Database {

    private const val URL = "jdbc:sqlite:./plugins/RPG/database.db"
    private const val DRIVER = "org.sqlite.JDBC"
    val tableList = ArrayList<Table>()

    fun connect() {
        try {
            TransactionManager.defaultDatabase = Database.connect(URL, DRIVER)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //Bukkit.getLogger().info("db loaded")
    }

    fun createTables() {

        transaction {
            addLogger(StdOutSqlLogger)

            tableList.forEach {
                SchemaUtils.create(it)
            }
        }
    }

    fun writeUser(player: Player) {
        UserData.run {
            if (isExist(player)) updateData(player)
            else insertData(player)
        }
    }

    fun readUser(player: Player) {
        val playerData = UserData.get(player)
        if (playerData.isNotEmpty()) UserData.read(player, playerData[0])
    }
}
