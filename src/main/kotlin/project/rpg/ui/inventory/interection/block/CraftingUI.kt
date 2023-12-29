package project.rpg.ui.inventory.interection.block

import io.github.monun.heartbeat.coroutines.HeartbeatScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.CraftingInventory
import project.rpg.recipes.RecipeManager
import kotlin.time.Duration.Companion.microseconds

object CraftingUI{
    fun clickedBy(event: InventoryEvent) {
        action(if (event.inventory is CraftingInventory && event.inventory.type != InventoryType.CRAFTING) {event.inventory as CraftingInventory} else null)

        if(event is InventoryClickEvent){
            val table = event.inventory
            if (table is CraftingInventory && event.slot == 0) { // 작업대 출력 칸일때

                val matrix = table.matrix.asList()
                val player = event.whoClicked as Player
                val maxCraft = RecipeManager.getMaxRecipeCount(matrix)
                val resultItem = RecipeManager.getRecipe(matrix)?.resultItem

                if(resultItem != null){
                    event.isCancelled = true
                    var crafts = maxCraft

                    if (event.isShiftClick) {
                        resultItem.amount = maxCraft
                        player.inventory.addItem(resultItem)
                    }
                    else {
                        player.setItemOnCursor(resultItem)
                        crafts = 1
                    }
                    decreaseAll(table, crafts)
                }
            }
        }
    }

    fun closedBy(event: InventoryCloseEvent) {
        val inv = event.inventory
        if(inv is CraftingInventory) {
            val player = event.player as Player
            HeartbeatScope().launch {
                delay(5L)
                inv.matrix.forEach {
                    if(it!=null) player.inventory.addItem(it)
                }
            }
        }
    }

    private fun action(table:CraftingInventory?){
        if(table == null) return
        HeartbeatScope().launch {
            delay(1.microseconds)
            getCustomOrDefaultRecipe(table)
        }
    }

    private fun getCustomOrDefaultRecipe(table:CraftingInventory){
        if(table.result == null) {
            val result = RecipeManager.getRecipe(table.matrix.asList())
            table.result = result?.result
        }
    }

    private fun decreaseAll(table: CraftingInventory, count: Int) {
        table.result = null
        val newMatrix = Array(9) {
            val item = table.matrix[it]
            table.matrix[it] = null
            item
        }
        newMatrix.forEach {
            if (it != null) {
                it.amount -= count
            }
        }
        table.matrix = newMatrix
        action(table)
    }
}