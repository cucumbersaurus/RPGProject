package project.rpg.ui.inventory.interection.block

import net.kyori.adventure.text.Component.text
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import project.rpg.ui.inventory.GuiBase

class CraftingUI(player: Player) : GuiBase(player, text("아이템 제작")) {

    override fun initialize() {
        for(i in 0..53){
            when(i){
                10, 11, 12, 19, 20, 21, 28, 29, 30, 24 -> continue
            }
            setItem(
                Material.WHITE_STAINED_GLASS_PANE,
                i,
                {event:InventoryClickEvent, _:Int->
                    event.isCancelled = true
                }
            )
        }
    }
}