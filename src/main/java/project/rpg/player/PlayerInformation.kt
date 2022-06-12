package project.rpg.player

import org.bukkit.entity.Player
import project.rpg.manager.AttributeManager
import project.rpg.player.info.Mana
import project.rpg.player.info.Skill
import project.rpg.player.info.Status
import project.rpg.ui.ActionBarUI

object PlayerInformation {
    fun makeInfo(player: Player) {
        ActionBarUI.addPlayer(player)
        AttributeManager.setAttributes(player, Status(player))
        player.healthScale = Status.getPlayerMap()[player.uniqueId]!!.health / 100.0
        player.isHealthScaled = true
        player.health = Status.getPlayerMap()[player.uniqueId]!!.health / 100.0
        Mana.addPlayer(player)
        Skill(player, null)
    }

    fun deleteInfo(player: Player) {
        ActionBarUI.deletePlayer(player)
    }
}