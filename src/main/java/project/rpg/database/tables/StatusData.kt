package project.rpg.database.tables

import org.bukkit.entity.Player
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import project.rpg.database.Database
import project.rpg.extensions.*

object StatusData : Table() {

    val id = integer("id").autoIncrement().uniqueIndex()

    private val agility = integer("agility")
    private val defense = integer("defense")
    private val handicraft = integer("handicraft")
    val health = integer("health")
    private val intelligence = integer("intelligence")
    private val luck = integer("luck")
    val speed = integer("speed")
    private val strength = integer("strength")
    private val additionalPoints = integer("additionalPoints")

    override val primaryKey by lazy {
        super.primaryKey ?: PrimaryKey(id)
    }

    fun initialize() {
        Database.tableList.add(this)
    }

    fun get(id: Int): ResultRow {
        return transaction {
            select { StatusData.id eq id }.toList()[0]
        }
    }

    fun insertData(player: Player): Int {

        return transaction {
            insert {
                it[agility] = player.status.agility
                it[defense] = player.status.defense
                it[handicraft] = player.status.handicraft
                it[health] = player.status.maxHealth
                it[intelligence] = player.status.intelligence
                it[luck] = player.status.luck
                it[speed] = player.status.speed
                it[strength] = player.status.strength
                it[additionalPoints] = player.status.additionalStatusPoint
            } get StatusData.id
        }
    }

    fun updateData(player: Player, id: Int) {
        transaction {
            update({ StatusData.id eq id }) {
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

    fun read(player: Player, id: Int) {
        val statusData = get(id)

        player.status.agility = statusData[agility]
        player.status.defense = statusData[defense]
        player.status.handicraft = statusData[handicraft]
        player.status.maxHealth = statusData[health]
        player.status.intelligence = statusData[intelligence]
        player.status.luck = statusData[luck]
        player.status.speed = statusData[speed]
        player.status.strength = statusData[strength]
        player.status.additionalStatusPoint = statusData[additionalPoints]
    }

}