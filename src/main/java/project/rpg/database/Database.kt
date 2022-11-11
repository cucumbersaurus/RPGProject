package project.rpg.database

import org.bukkit.entity.Player
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import project.rpg.database.StatusData.agility
import project.rpg.database.StatusData.defense
import project.rpg.database.StatusData.handicraft
import project.rpg.database.StatusData.health
import project.rpg.database.StatusData.intelligence
import project.rpg.database.StatusData.luck
import project.rpg.database.StatusData.speed
import project.rpg.database.StatusData.strength
import project.rpg.database.UserData.statusData
import project.rpg.extensions.*

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



    fun writeUser(player: Player){

        val uuidList = transaction {
            UserData.select { UserData.uuid eq player.uniqueId }.toList()
        }

        if (uuidList.isEmpty()) {
            transaction {

                UserData.insert {
                    it[uuid] = player.uniqueId
                    it[username] = player.name
                    it[statusData] = insertStatus(player)
                }
            }
        }
        else{
            transaction {
                UserData.update({ UserData.uuid eq player.uniqueId}) {
                    val statusId = transaction {
                        UserData.select{ uuid eq player.uniqueId}.toList()[0][statusData]
                    }
                    it[username] = player.name
                    updateStatus(player, statusId)
                }
            }
        }
    }

    fun insertStatus(player: Player):Int{

        return transaction {
            StatusData.insert {
                it[agility] = player.status.agility
                it[defense] = player.status.defense
                it[handicraft] = player.status.handicraft
                it[health] = player.status.maxHealth
                it[intelligence] = player.status.intelligence
                it[luck] = player.status.luck
                it[speed] = player.status.speed
                it[strength] = player.status.strength
            }.insertedCount
        }
    }

    fun updateStatus(player: Player, id: Int){
        transaction {
            StatusData.update({StatusData.id eq id}) {
                it[agility] = player.status.agility
                it[defense] = player.status.defense
                it[handicraft] = player.status.handicraft
                it[health] = player.status.maxHealth
                it[intelligence] = player.status.intelligence
                it[luck] = player.status.luck
                it[speed] = player.status.speed
                it[strength] = player.status.strength
            }
        }
    }

    fun readUser(player:Player){
        val playerData = transaction {
            UserData.select { UserData.uuid eq player.uniqueId }.toList()
        }

        val statusData = transaction {
            StatusData.select { StatusData.id eq playerData[0][statusData]}.toList()
        }[0]

        player.status.agility = statusData[agility]
        player.status.defense = statusData[defense]
        player.status.handicraft = statusData[handicraft]
        player.status.maxHealth = statusData[health]
        player.status.intelligence = statusData[intelligence]
        player.status.luck = statusData[luck]
        player.status.speed = statusData[speed]
        player.status.strength = statusData[strength]
    }
}
