package project.rpg.ui.inventory.menu.dictionary

import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import project.rpg.items.Items
import project.rpg.textComponets.color.DefaultTextColors
import project.rpg.ui.inventory.GuiBase

class ItemDictionaryUI(player: Player) : GuiBase(player, 54, text("아이템 도감")) {
    override fun init(player: Player) {
        setFrame()
        setItem(text("닫기"), null, Material.BARRIER, 1, 49, Button.CLOSE.name, false)

        for(i in Items.values()){
            val slot = 9*(i.value/7)+i.value%7+10
            if(i.value==0){
                setItem(text("NULL").color(TextColor.color(DefaultTextColors.RED.color)), null, Material.BARRIER, 1, slot, Button.ITEM.name, false)
            }
            else setItem(i.item?.clone(), slot, Button.ITEM.name)
        }
    }

    override fun onClick(event: InventoryClickEvent) {
        event.isCancelled = true
        val player = event.whoClicked as Player
        when (getValue(event.slot)) {
            Button.BACKGROUND.name ->{}
            Button.ITEM.name ->{
                player.inventory.addItem(Items.values()[slotToItem(event.slot)].item!!)
                player.playSound(player.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1f)
            }
            Button.CLOSE.name ->{
                forceCloseGUI(player)
            }
        }
    }

    private fun setFrame(){
        for (i in 0..8){
            setItem(text(" "), null, Material.WHITE_STAINED_GLASS_PANE, 1, i, Button.BACKGROUND.name, false)

        }
        for (i in 9..53 step 9){
            setItem(text(" "), null, Material.WHITE_STAINED_GLASS_PANE, 1, i, Button.BACKGROUND.name, false)

        }
        for (i in 17..53 step 9){
            setItem(text(" "), null, Material.WHITE_STAINED_GLASS_PANE, 1, i, Button.BACKGROUND.name, false)

        }
        for (i in 46..53){
            setItem(text(" "), null, Material.WHITE_STAINED_GLASS_PANE, 1, i, Button.BACKGROUND.name, false)

        }
    }

    private fun slotToItem(slot: Int):Int{
        return (slot-8)-2*(slot/9)
    }

    private enum class Button {
        BACKGROUND,
        ITEM,
        CLOSE,
    }

}