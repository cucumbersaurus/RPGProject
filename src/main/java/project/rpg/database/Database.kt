package project.rpg.database

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction

object Database {

    private const val url = "jdbc:sqlite:./plugins/RPG/database.db"
    private const val driver = "org.sqlite.JDBC"

    fun connect() {
        try {
            TransactionManager.defaultDatabase = Database.connect(url, driver)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //Bukkit.getLogger().info("db loaded")
    }

    fun createTables(){
        try {
            //connect()
            transaction{
                addLogger(StdOutSqlLogger)

                SchemaUtils.create(UserData, StatusData)

            }
        } catch (e:Exception) {
            e.printStackTrace()
        }
    }

}
