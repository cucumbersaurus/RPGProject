package project.rpg.database.tables

import org.bukkit.entity.Player
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import project.rpg.database.Database
import project.rpg.extensions.levels

object LevelData : Table() {

    val id = integer("id").autoIncrement()//.uniqueIndex()

    private val level = long("level")
    private val exp = long("exp")

    override val primaryKey by lazy {
        super.primaryKey ?: PrimaryKey(id)
    }

    fun initialize() {
        Database.tableList.add(this)
    }

    fun insertData(player: Player): Int {
        return transaction {
            insert {
                it[level] = player.levels.level
                it[exp] = player.levels.exp
            } get LevelData.id
        }
    }

    fun updateData(player: Player, id: Int) {
        transaction {
            LevelData.update({ LevelData.id eq id }) {
                it[level] = player.levels.level
                it[exp] = player.levels.exp
            }
        }
    }

    fun read(player: Player, id: Int) {
        val levelData = transaction {
            LevelData.select { LevelData.id eq id }.toList()[0]
        }

        player.levels.level = levelData[level]
        player.levels.exp = levelData[exp]
    }

}