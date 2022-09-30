package project.rpg.player.status.objects

import kotlinx.serialization.Serializable
import org.bukkit.entity.Player
import project.rpg.player.status.base.StatusBase
import project.rpg.player.status.base.StatusName

@Serializable
class Luck: StatusBase(StatusName.LUCK) {
    override fun effect(player: Player?) {
        TODO("구현 예정")
    }
}