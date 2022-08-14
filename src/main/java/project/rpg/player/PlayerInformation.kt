package project.rpg.player

import org.bukkit.entity.Player
import project.rpg.player.info.Skill
import project.rpg.player.status.base.StatusName
import project.rpg.ui.text.ActionBarUI

object PlayerInformation {
    fun makeInfo(player: Player) {
        val user = User.newUser(player)
        user.status.reloadMap()

        player.maxHealth = User.getPlayer(player).status.getStatusValues(StatusName.HEALTH) / 10.0
        player.healthScale = 100.0
        player.health = 1.0//User.getPlayer(player).status.getStatusValues(StatusName.HEALTH) / 10.0\

        Skill(player)

        ActionBarUI.addPlayer(player)
    }

    fun deleteInfo(player: Player) {
        ActionBarUI.deletePlayer(player)
        User.removeUser(player)
    }

    @JvmStatic
    fun updateHealth( player: Player){
        player.maxHealth = User.getPlayer(player).status.getStatusValues(StatusName.HEALTH) / 10.0
    }
}