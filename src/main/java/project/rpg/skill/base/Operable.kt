package project.rpg.skill.base

import org.bukkit.entity.Player
import org.bukkit.event.block.Action

interface Operable {
    fun onEnable(player: Player, action: Action?)
}