package project.rpg.ui.inventory

import net.kyori.adventure.text.Component.text
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent

class CustomCraftingUI(player: Player) : GuiBase(player, 54, text("아이템 제작")) {

    override fun init(player: Player) {
        for(i in 0..53){
            when(i){
                10 -> continue
                11 -> continue
                12 -> continue
                19 -> continue
                20 -> continue
                21 -> continue
                28  -> continue
                29 -> continue
                30 -> continue
                24 -> continue
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