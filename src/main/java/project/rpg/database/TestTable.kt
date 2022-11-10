package project.rpg.database

import org.jetbrains.exposed.sql.Table

object Users : Table() {
    val id = integer("id").autoIncrement()
    val fullName = varchar("fullname", 50)
    val userName = varchar("username", 50)
    val password = varchar("keypass", 30)
    val email = varchar("email", 80)
    override val primaryKey by lazy {
        super.primaryKey ?: PrimaryKey(id)
    }

}