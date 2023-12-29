package project.rpg.events.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.*
import project.rpg.ui.inventory.GuiBase
import project.rpg.ui.inventory.interection.block.CraftingUI

class InventoryEventListener : Listener {
    @EventHandler
    fun guiClick(event: InventoryClickEvent) {
        CraftingUI.clickedBy(event)
        GuiBase.clickedBy(event)
    }

    @EventHandler
    fun guiInteraction(event: InventoryDragEvent) {
        CraftingUI.clickedBy(event)
    }
    @EventHandler
    fun guiInteraction(event: InventoryInteractEvent) {
        CraftingUI.clickedBy(event)
    }

    @EventHandler
    fun guiClose(event: InventoryCloseEvent) {
        GuiBase.closedBy(event)
        CraftingUI.closedBy(event)
    }

    @EventHandler
    fun craft(event:PrepareItemCraftEvent){

    }
}