package project.rpg.ui.inventory.interection.block

import io.github.monun.heartbeat.coroutines.HeartbeatScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.bukkit.event.inventory.InventoryEvent
import org.bukkit.event.inventory.InventoryMoveItemEvent
import org.bukkit.inventory.CraftingInventory
import project.rpg.recipes.RecipeManager

object CraftingUI{
    fun clickedBy(event: InventoryEvent) = action(if (event.inventory is CraftingInventory) {event.inventory as CraftingInventory} else null)
    fun clickedBy(event: InventoryMoveItemEvent) {
        action(if (event.initiator is CraftingInventory) {event.initiator as CraftingInventory} else null)
        action(if (event.source is CraftingInventory) {event.source as CraftingInventory} else null)
        action(if (event.destination is CraftingInventory) {event.destination as CraftingInventory} else null)
    }

    private fun action(table:CraftingInventory?){
        if(table == null) return
        HeartbeatScope().launch {
            delay(1L)
            val result = RecipeManager.getRecipe(table.matrix.asList())
            table.result = result?.result
            //Bukkit.broadcastMessage("event, ${table.result?.type}")
        }
    }

}