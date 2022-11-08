package project.rpg.player.status.objects

import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import project.rpg.player.status.base.StatusBase
import project.rpg.player.status.base.StatusName

class Speed: StatusBase(StatusName.SPEED) {
    override fun effect(player: Player?) {
        player!!.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)!!.baseValue = 0.1 + (value - 10.0) / 1000.0
    }
}