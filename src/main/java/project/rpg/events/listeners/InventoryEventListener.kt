package project.rpg.events.listeners

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import project.rpg.ui.inventory.GuiBase

class InventoryEventListener : Listener {
    @EventHandler
    fun guiClick(event: InventoryClickEvent) {
        val gui = GuiBase.getGUI(event.whoClicked as Player)
        gui?.onClick(event)
        gui?.onClickEvent(event)
    }

    @EventHandler
    fun guiClose(e: InventoryCloseEvent) {
        val gui = GuiBase.getGUI(e.player as Player)
        gui?.closeGUI(e)
    }
}