package project.rpg.player.status.objects

import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import project.rpg.player.status.base.StatusBase
import project.rpg.player.status.base.StatusName

class Defense : StatusBase(StatusName.DEFENSE) {
    override fun effect(player: Player?) {
        player!!.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)!!.baseValue = (value - 10.0) / 100.0
    }
}