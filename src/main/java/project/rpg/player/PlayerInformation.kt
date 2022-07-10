package project.rpg.player

import org.bukkit.entity.Player
import project.rpg.player.info.Skill
import project.rpg.player.status.base.StatusName
import project.rpg.ui.ActionBarUI

object PlayerInformation {
    fun makeInfo(player: Player) {
        val human = Human.newHuman(player)
        human.status.reloadMap()

        player.healthScale = Human.getPlayer(player).status.getStatus(StatusName.HEALTH) / 10.0
        player.isHealthScaled = true
        player.health = Human.getPlayer(player).status.getStatus(StatusName.HEALTH) / 10.0

        Skill(player)

        ActionBarUI.addPlayer(player)
    }

    fun deleteInfo(player: Player) {
        ActionBarUI.deletePlayer(player)
    }

    @JvmStatic
    fun updateHealth( player: Player){
        player.healthScale = Human.getPlayer(player).status.getStatus(StatusName.HEALTH) / 10.0
    }
}