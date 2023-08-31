package project.rpg.skills.base

import org.bukkit.entity.Player
import org.bukkit.event.block.Action

abstract class ActiveSkillBase : SkillBase() {
    override fun onEnable(player: Player, action: Action?) {
        if (coolTime == 0) {
            onEnable(player, action)
            coolTime = skillTime
        } else {
            sendActionBar(player)
        }
    }
}