package project.rpg.database

import org.bukkit.Bukkit
import java.sql.Connection
import java.sql.DriverManager

object Database {

    fun getConnection(/*force: Boolean, startup: Boolean, onlyCheckTransacting: Boolean, waitTime: Int*/): Connection? {
        var connection: Connection? = null
        try {
            val database = "jdbc:sqlite:" + "plugins/RPG/" + "database.db"
            connection = DriverManager.getConnection(database)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        Bukkit.getLogger().info("db loaded")
        return connection
    }

    fun createTables(){
        val prefix = "rpg_"
        try {
            getConnection(/*true, true, true, 0*/).use { connection ->
                if (connection != null) {
                    var index = ""
                    val statement = connection.createStatement()

                    index = "INDEX(id)"
                    statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + prefix + "player_map (" +
                            "rowid int NOT NULL," +
                            "name varchar(255)," +
                            "PRIMARY KEY (rowid)" +
                            ") "
                    )


                    statement.close()

                }
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
}
