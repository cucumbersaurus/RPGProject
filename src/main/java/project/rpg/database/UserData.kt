package project.rpg.database

import org.jetbrains.exposed.sql.Table

object UserData: Table() {

    val uuid = uuid("uuid").uniqueIndex()
    val username = varchar("username", 16)
    val statusData = integer("statusData")

    override val primaryKey by lazy {
        super.primaryKey ?: PrimaryKey(uuid)
    }

}