package project.rpg.events.listeners

import org.bukkit.Material
import org.bukkit.entity.Pose
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockFromToEvent
import org.bukkit.event.player.PlayerInteractEvent

class BlockClickEventListener : Listener {
    @EventHandler
    fun blockFromTo(event: BlockFromToEvent) {
        if (event.block.type == Material.DRAGON_EGG) { //드래곤알 tp 방지
            event.isCancelled = true
        }
    }

    @EventHandler
    fun playerBlockInteract(event: PlayerInteractEvent) {
        if (event.clickedBlock == null) return
        when (event.clickedBlock!!.type) {
            Material.ENCHANTING_TABLE -> if (isOnlyInteract(event)) {
                event.isCancelled = true
                //ui 오픈
            }
            Material.ANVIL, Material.CHIPPED_ANVIL, Material.DAMAGED_ANVIL -> {/*ui 오픈*/ }
            else -> {event.isCancelled = false}

        }
    }

    private fun isOnlyInteract(event: PlayerInteractEvent): Boolean { //단순 우클릭만 ui 오픈
        return if (event.action.isRightClick) {
            event.player.pose != Pose.SNEAKING
        } else false
    }
}