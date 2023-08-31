package project.rpg.skills.base

import org.bukkit.entity.Player
import org.bukkit.event.block.Action

interface Operable {

    /**
     * @param player 작동시킨 플레이어
     * @param action 작동시킬 때 동작
     */
    fun onEnable(player: Player, action: Action?)
}