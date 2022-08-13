package project.rpg.ui.inventory.menu

import net.kyori.adventure.text.Component.text
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import project.rpg.ui.inventory.GuiBase
import project.rpg.ui.inventory.menu.dictionary.ItemDictionaryUI

class DictionaryMenu(player: Player) : GuiBase(player, 54, text("도감")) {
    override fun initialize(player: Player) {
        for (i in 0..53) {
            setItem(text(" "), null, Material.WHITE_STAINED_GLASS_PANE, 1, i, Button.BACKGROUND.name, false)
        }
        setItem(text("아이템 도감"), null, Material.LAVA_BUCKET, 1, 10, Button.ITEMS.name, false)
        setItem(text("스킬 도감"), null, Material.IRON_SWORD, 1, 12, Button.SKILLS.name, false)
        setItem(text("직업 도감"), null, Material.LECTERN, 1, 14, Button.JOBS.name, false)
        setItem(text("닫기"), null, Material.BARRIER, 1, 49, Button.CLOSE.name, false)

    }

    override fun onClick(event: InventoryClickEvent) {
        event.isCancelled = true
        val player = event.whoClicked as Player
        when (getValue(event.slot)) {
            Button.BACKGROUND.name -> {
                return
            }
            Button.ITEMS.name -> {
                forceCloseGUI(player)
                ItemDictionaryUI(player)
            }
            Button.SKILLS.name -> {
                return
            }
            Button.JOBS.name -> {
                return
            }
            Button.CLOSE.name -> forceCloseGUI(player)
            else -> {
                return
            }
        }
    }

    private enum class Button{
        BACKGROUND,
        ITEMS,
        SKILLS,
        JOBS,
        CLOSE,

    }

}