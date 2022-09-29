package project.rpg.player.status.objects

import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import project.rpg.player.status.base.StatusBase
import project.rpg.player.status.base.StatusName

class Agility: StatusBase(StatusName.AGILITY) {
    override fun effect(player: Player?) {
        player!!.getAttribute(Attribute.GENERIC_ATTACK_SPEED)!!.baseValue = 4.0 + (value - 10.0) / 10.0
    }
}