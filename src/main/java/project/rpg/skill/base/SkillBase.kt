package project.rpg.skill.base

import kotlinx.serialization.Serializable
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player
import org.bukkit.event.block.Action

@Serializable
abstract class SkillBase(
    var name: String? = null,
    var description: String? = null,
    protected var skillTime: Int = 0,
    protected var coolTime: Int = 0,
) : Operable {

    override fun onEnable(player: Player, action: Action?) {}
    fun sendActionBar(p: Player) {
        p.sendActionBar(Component.text((coolTime / 20).toString() + "초 남음"))
    }

    fun minTime() {
        if (coolTime > 0) {
            --coolTime
        }
    }
}