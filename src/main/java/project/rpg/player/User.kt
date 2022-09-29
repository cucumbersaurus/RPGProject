package project.rpg.player

import org.bukkit.entity.Player
import project.rpg.player.info.Friends
import project.rpg.player.info.Skill
import project.rpg.player.job.Job
import project.rpg.player.level.Levels
import project.rpg.player.mana.Mana
import project.rpg.player.name.Title
import project.rpg.player.status.Status
import java.util.*

class User private constructor(val player: Player)  {
    var title: Title = Title(player)
    var status: Status = Status(player)
    var mana: Mana = Mana(status)
    var levels: Levels = Levels(player)
    var job: Job = Job(player)
    var skill: Skill = Skill(player)
    var friends: Friends = Friends(player)

    fun saveMap() {
        playerMap[player.uniqueId] = this
    }

    companion object {
        @JvmStatic
        private val playerMap: MutableMap<UUID, User> = HashMap()

        @JvmStatic
        fun newUser(player: Player): User {
            val user = User(player)
            playerMap[player.uniqueId] = user
            return user
        }

        @JvmStatic
        fun removeUser(player: Player) {
            playerMap.remove(player.uniqueId)
        }

        @JvmStatic
        fun getPlayer(player: Player): User? {
            return playerMap[player.uniqueId]
        }

        @JvmStatic
        val playerList: List<User>
            get() = ArrayList(playerMap.values)
    }
}