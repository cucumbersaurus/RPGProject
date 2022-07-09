package project.rpg.player

import org.bukkit.entity.Player
import project.rpg.player.info.Skill
import project.rpg.player.status.base.StatusName
import project.rpg.skill.magic.fire.MeteorStrike
import project.rpg.ui.ActionBarUI

object PlayerInformation {
    fun makeInfo(player: Player) {
        Human.addPlayer(player)

        Human.getPlayer(player).stats.reloadMap()

        player.healthScale = Human.getPlayer(player).stats.getStatus(StatusName.HEALTH.name) / 100.0
        player.isHealthScaled = true
        player.health = Human.getPlayer(player).stats.getStatus(StatusName.HEALTH.name) / 100.0

        Skill(player,  MeteorStrike(player))

        ActionBarUI.addPlayer(player)
    }

    fun deleteInfo(player: Player) {
        ActionBarUI.deletePlayer(player)
    }

    @JvmStatic
    fun updateHealth( player: Player){
        player.healthScale = Human.getPlayer(player).stats.getStatus(StatusName.HEALTH.name) / 100.0
    }
}