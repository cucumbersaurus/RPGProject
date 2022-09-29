package project.rpg.player.status.objects

import org.bukkit.entity.Player
import project.rpg.extensions.mana
import project.rpg.player.status.base.StatusBase
import project.rpg.player.status.base.StatusName

class Intelligence : StatusBase(StatusName.INTELLIGENCE) {
    override fun effect(player: Player?) {
        if (player != null) {
            player.mana.maxMana = value*10
        }
    }

    companion object {
        fun deserialize(map: Map<String?, String>): Intelligence {
            val intelligence = Intelligence()
            intelligence.value = map["value"]!!.toInt()
            return intelligence
        }
    }
}