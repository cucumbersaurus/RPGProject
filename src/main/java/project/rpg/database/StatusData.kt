package project.rpg.database

import org.jetbrains.exposed.sql.Table

object StatusData: Table() {

    val id = integer("id").autoIncrement()

    val agility = integer("agility")
    val defense = integer("defense")
    val handicraft = integer("handicraft")
    val health = integer("health")
    val intelligence = integer("intelligence")
    val luck = integer("luck")
    val speed = integer("speed")
    val strength = integer("strength")

    override val primaryKey by lazy{
        super.primaryKey ?: PrimaryKey(id)
    }

}