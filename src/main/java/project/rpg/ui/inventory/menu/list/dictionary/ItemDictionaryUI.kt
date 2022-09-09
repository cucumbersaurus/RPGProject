package project.rpg.ui.inventory.menu.list.dictionary

import net.kyori.adventure.text.Component.text
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import project.rpg.extensions.setDisplayName
import project.rpg.items.Items
import project.rpg.textComponets.color.DefaultTextColors
import project.rpg.ui.inventory.menu.list.ListUIBase

class ItemDictionaryUI(player: Player) : ListUIBase<Items>(player, text("아이템 도감"), Items.values()) {

    private var currentPage = 0
    private val totalPages = Items.values().size/28
    override fun convertToItemStack(source: Items): ItemStack {
        lateinit var item: ItemStack
        if(source.value == 0){
            item = ItemStack(Material.BARRIER)
            item.setDisplayName(text("NULL").color(DefaultTextColors.RED.color))
        }
        else {
            item = source.item!!.clone()
        }
        return item
    }

    override fun initialize(player: Player) = setItems()

    override fun onClick(event: InventoryClickEvent) {
        event.isCancelled = true
        val player = event.whoClicked as Player
        when (getValue(event.slot)) {
            Button.BACKGROUND.name -> return
            Button.ITEM.name ->{
                val id = slotToIndex(event.slot)
                if(id<Items.values().size) {
                    val item = Items.values()[id].item
                    if (item != null && player.isOp) {
                        player.inventory.addItem(item)
                        player.playSound(player.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1f)
                    }
                }
            }
            Button.CLOSE.name ->{
                forceCloseGUI()
            }
            Button.NEXT.name->{
                currentPage+=1
                reloadUI()
            }
            Button.PREIVIOUS.name->{
                currentPage-=1
                reloadUI()
            }
        }
    }

    private enum class Button {
        BACKGROUND,
        ITEM,
        CLOSE,
        NEXT,
        PREIVIOUS,
    }

}