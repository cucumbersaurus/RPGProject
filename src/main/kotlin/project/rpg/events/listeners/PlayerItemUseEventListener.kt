package project.rpg.events.listeners

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import project.rpg.Rpg
import project.rpg.items.Items
import project.rpg.items.disposable.DisposableBase
import project.rpg.items.weapon.MagicItemBase

class PlayerItemUseEventListener() : Listener {

    @EventHandler
    fun itemUseEvent(event: PlayerInteractEvent) {
        val player = event.player
        val item = event.item

        if (item != null) {
            /*if(checkWorldEditWand(item)){
                event.isCancelled=true
                return
            }*/
            //else{
            if (item.itemMeta.hasCustomModelData()) {
                val id = item.itemMeta.customModelData
                val usedItem = Items.values()[id]
                if (usedItem.itemBase is MagicItemBase) {
                    usedItem.itemBase.onEnable(player, event.action)
                    Rpg.actionBar.updateActionBar(player)
                    event.isCancelled = true
                } else if (usedItem.itemBase is DisposableBase) {
                    usedItem.itemBase.onUse(player)
                    //TODO : 아이템 갯수 하나 줄이기
                }
            }
            //}
        }
    }

    private fun checkWorldEditWand(item: ItemStack): Boolean {
        return when (item.type) {
            Material.WOODEN_AXE, Material.COMPASS -> {
                true
            }

            else -> {
                false
            }
        }
    }
}