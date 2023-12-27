package project.rpg.ui.inventory.interection.block

import io.github.monun.heartbeat.coroutines.HeartbeatScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.bukkit.entity.Player
import org.bukkit.event.inventory.*
import org.bukkit.inventory.CraftingInventory
import project.rpg.recipes.RecipeManager

object CraftingUI{
    fun clickedBy(event: InventoryEvent) {
        //Bukkit.broadcastMessage("ac")
        action(if (event.inventory is CraftingInventory && event.inventory.type != InventoryType.CRAFTING) {event.inventory as CraftingInventory} else null)
        if(event is InventoryClickEvent){

            val table = event.inventory
            if(table is CraftingInventory && event.slot == 0) { // 작업대 출력 칸일때
                val matrix = table.matrix.asList()
                val player = event.whoClicked as Player
//
//                matrix.forEach {
//                    Bukkit.broadcastMessage(it.toString() + " bef")
//                }

                val maxCraft = RecipeManager.getMaxRecipeCount(matrix)
                val result = RecipeManager.getRecipe(matrix)

                val resultItem = result?.result?.clone()
                resultItem?.amount = maxCraft - 1 //하나는 클릭하면 이미 인벤에 들어옴


                if (event.isShiftClick) {
                    if (resultItem != null && maxCraft > 1) {
                        player.inventory.addItem(resultItem)
                    }
                    //decreaseAll(table, maxCraft)
                }
                else null//decreaseAll(table, 1)
//                HeartbeatScope().launch {
//                    delay(2L)
//                    table.matrix.forEach {
//                        Bukkit.broadcastMessage(it.toString() + " af")
//                    }
//                }
            }
        }
    }
    fun clickedBy(event: InventoryMoveItemEvent) {
        action(if (event.initiator is CraftingInventory && event.initiator.type != InventoryType.CRAFTING) {event.initiator as CraftingInventory} else null)
        action(if (event.source is CraftingInventory && event.source.type != InventoryType.CRAFTING) {event.source as CraftingInventory} else null)
        action(if (event.destination is CraftingInventory && event.destination.type != InventoryType.CRAFTING) {event.destination as CraftingInventory} else null)
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
            delay(1L)
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

        val newMatrix = table.matrix.copyOf()
        table.matrix.forEach {
            it?.amount =0
        }
        //개수가 이상해지는 버그가 있어서 관련 코드가 이상해짐)
        HeartbeatScope().launch {
            delay(1L)
            newMatrix.forEach {
                if (it != null) {
                    if(it.amount%2==0) {
                        it.amount += 2
                        it.amount /= 2
                        if (it.amount <= count) {
                            it.amount = 0
                        } else it.amount -= count
                    }
                    else{
                        it.amount += 2
                        it.amount /=2
                        if (it.amount <= count-1) {
                            it.amount = 0
                        } else it.amount -= count-1
                    }
                }
            }

            table.matrix = newMatrix
            table.result = null
            action(table)
        }
    }
}