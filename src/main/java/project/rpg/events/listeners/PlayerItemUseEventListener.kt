package project.rpg.events.listeners

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import project.rpg.Rpg
import project.rpg.items.Items
import project.rpg.items.weapon.WeaponBase

class PlayerItemUseEventListener(private val plugin: Rpg) : Listener {

    @EventHandler
    fun itemUseEvent(event: PlayerInteractEvent) {
        val player = event.player
        val item = event.item

        if(item!=null){
            /*if(checkWorldEditWand(item)){
                event.isCancelled=true
                return
            }*/
            //else{
                if(item.itemMeta.hasCustomModelData()) {
                    val id = item.itemMeta.customModelData
                    val usedItem = Items.values()[id]
                    if (usedItem.itemBase is WeaponBase) {
                        usedItem.itemBase.onEnable(event.action, player)
                        plugin.actionBar.updateActionBar(player)
                        event.isCancelled=true
                    }
                }
            //}
        }
    }

    private fun checkWorldEditWand(item:ItemStack):Boolean{
        return when(item.type) {
            Material.WOODEN_AXE, Material.COMPASS->{
                true
            }
            else -> {
                false
            }
        }
    }
}