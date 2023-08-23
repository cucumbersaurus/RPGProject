package project.rpg.database

import org.bukkit.entity.Player
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object UserData : Table() {

    private val uuid = uuid("uuid").uniqueIndex()
    private val username = varchar("username", 16)
    private val statusData = integer("statusData").uniqueIndex()
    private val jobData = integer("jobData").uniqueIndex()
    private val levelData = integer("levelData").uniqueIndex()

    override val primaryKey by lazy {
        super.primaryKey ?: PrimaryKey(uuid)
    }

    fun initialize() {
        Database.tableList.add(this)
    }

    fun isExist(player: Player): Boolean {
        val uuidList = transaction {
            UserData.select { uuid eq player.uniqueId }.toList()
        }
        return uuidList.isNotEmpty()
    }

    fun get(player: Player): List<ResultRow> {
        return transaction {
            select { uuid eq player.uniqueId }.toList()
        }
    }

    fun insertData(player: Player) {
        transaction {
            insert {
                it[uuid] = player.uniqueId
                it[username] = player.name
                it[statusData] = StatusData.insertData(player)
                it[jobData] = JobData.insertData(player)
                it[levelData] = LevelData.insertData(player)
            }
        }
    }

    fun updateData(player: Player) {
        transaction {
            UserData.update({ uuid eq player.uniqueId }) {

                val playerData = transaction {
                    UserData.select { uuid eq player.uniqueId }.toList()[0]
                }

                it[username] = player.name
                StatusData.updateData(player, playerData[statusData])
                JobData.updateData(player, playerData[jobData])
                LevelData.updateData(player, playerData[levelData])
            }
        }
    }

    fun read(player: Player, playerData: ResultRow) {
        StatusData.read(player, playerData[statusData])
        JobData.read(player, playerData[jobData])
        LevelData.read(player, playerData[levelData])
    }
}