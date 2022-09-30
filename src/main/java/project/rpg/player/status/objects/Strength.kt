package project.rpg.player.status.objects

import kotlinx.serialization.Serializable
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import project.rpg.player.status.base.StatusBase
import project.rpg.player.status.base.StatusName

@Serializable
class Strength: StatusBase(StatusName.STRENGTH) {
    override fun effect(player: Player?) {
        player!!.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)!!.baseValue = 2.0 + (value - 10.0) / 2.0
    }
}