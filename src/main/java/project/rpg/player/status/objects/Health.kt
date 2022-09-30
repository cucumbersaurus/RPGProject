package project.rpg.player.status.objects

import kotlinx.serialization.Serializable
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import project.rpg.player.PlayerInformation.updateHealth
import project.rpg.player.status.Status
import project.rpg.player.status.base.StatusBase
import project.rpg.player.status.base.StatusName
import java.util.*

@Serializable
class Health: StatusBase(StatusName.HEALTH) {
    override fun addValue(amount: Int, status: Status, player: Player?): Boolean {  //스텟 더하기
        if (status.additionalStatusPoint >= amount) {
            value += amount
            status.minAdditionalStatusPoint(amount)
            updateHealth(player!!)
            return true
        }
        return false
    }

    override fun effect(player: Player?) {
        Objects.requireNonNull(player!!.getAttribute(Attribute.GENERIC_MAX_HEALTH))?.baseValue =
            20.0 + (value - 10.0)
    }
}