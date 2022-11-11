package project.rpg.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import project.rpg.database.StatusData
import project.rpg.database.UserData
import project.rpg.extensions.*
import java.util.*


class SQLInsertCommand:CommandExecutor {

    lateinit var uniqueId: UUID
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {

        if(sender is Player) {
            uniqueId = sender.uniqueId
            insertUser(sender)
        }

        return true
    }

    private fun insertUser(player: Player){

        val uuidList = transaction {
            UserData.select { UserData.uuid eq uniqueId }.toList()
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
                UserData.update({ UserData.uuid eq uniqueId}) {
                    val statusId = transaction {
                        UserData.select{uuid eq uniqueId}.toList()[0][statusData]
                    }
                    it[username] = player.name
                    updateStatus(player, statusId)
                }
            }
        }
    }

    private fun insertStatus(player: Player):Int{

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

    private fun updateStatus(player: Player, id: Int){
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

}