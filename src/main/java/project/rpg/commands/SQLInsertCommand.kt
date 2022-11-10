package project.rpg.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import project.rpg.database.Users

class SQLInsertCommand:CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {

        Database.connect("jdbc:sqlite:./plugins/RPG/database.db", "org.sqlite.JDBC")
        transaction {
            Users.insert{
                it[fullName] = "ASDfsdf"
                it[userName]="userseruser"
                it[password]="asdfsadffasf"
                it[email]="DDDDD@sdaf.com"
            }        }

        return true
    }
}