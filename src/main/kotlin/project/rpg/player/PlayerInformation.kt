package project.rpg.player

import org.bukkit.entity.Player
import project.rpg.database.Database
import project.rpg.player.status.maxHealth
import project.rpg.player.status.base.StatusName
import project.rpg.ui.text.ActionBarUI

object PlayerInformation {
    fun makeInfo(player: Player) {
        val user = User.newUser(player)

        player.setAttributeMaxHealth(player.status.maxHealth / 10.0)
        player.healthScale = 100.0
        player.health = 1.0

        Database.readUser(player)
        user.status.applyChanges()

        ActionBarUI.addPlayer(player)
    }

    fun deleteInfo(player: Player) {
        Database.writeUser(player)
        ActionBarUI.deletePlayer(player)
        User.removeUser(player)
    }

    @JvmStatic
    fun updateHealth(player: Player) {
        player.setAttributeMaxHealth(player.status.getStatusValues(StatusName.HEALTH) / 10.0)
    }
}