package project.rpg.database

import org.bukkit.entity.Player
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction

object Database {

    private const val url = "jdbc:sqlite:./plugins/RPG/database.db"
    private const val driver = "org.sqlite.JDBC"
    val tableList = ArrayList<Table>()

    fun connect() {
        try {
            TransactionManager.defaultDatabase = Database.connect(url, driver)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //Bukkit.getLogger().info("db loaded")
    }

    fun createTables(){

        transaction{
            addLogger(StdOutSqlLogger)

            tableList.forEach{
                SchemaUtils.create(it)
            }
            /*
            SchemaUtils.create(
                UserData,
                StatusData,
                JobData,
                LevelData,
            )
            */

        }
    }

    fun writeUser(player: Player){
        UserData.run {
            if (isExist(player)) updateData(player)
            else insertData(player)
        }
    }

    fun readUser(player:Player){
        val playerData = UserData.get(player)
        if(playerData.isNotEmpty()) UserData.read(player, playerData[0])
    }
}
