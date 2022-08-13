package project.rpg.ui.inventory.blockInterection

import net.kyori.adventure.text.Component.text
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import project.rpg.ui.inventory.GuiBase

class CraftingUI(player: Player) : GuiBase(player, 54, text("아이템 제작")) {

    override fun initialize(player: Player) {
        for(i in 0..53){
            when(i){
                10, 11, 12, 19, 20, 21, 28, 29, 30, 24 -> continue
            }
            setItem(text(" "), null, Material.WHITE_STAINED_GLASS_PANE, 1, i, "background", false)
        }
    }

    override fun onClick(event: InventoryClickEvent) {
        val slot = event.rawSlot
        val player = event.whoClicked
        when(slot){
            10, 11, 12, 19, 20, 21, 28, 29, 30, 24 -> {
                event.isCancelled = false

            }
            else -> event.isCancelled = true
        }
    }
}