package project.rpg.player.status.objects

import kotlinx.serialization.Serializable
import org.bukkit.entity.Player
import project.rpg.player.status.base.StatusBase
import project.rpg.player.status.base.StatusName

@Serializable
class Handicraft: StatusBase(StatusName.HANDICRAFT) {
    override fun effect(player: Player?) {
        TODO("구현 예정")
    }
}