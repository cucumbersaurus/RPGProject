package project.rpg.ui.inventory.menu

import net.kyori.adventure.text.Component.text
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import project.rpg.ui.inventory.GuiBase
import project.rpg.ui.inventory.menu.list.dictionary.ItemDictionaryUI

class DictionaryMenu(player: Player) : GuiBase(player, 54, text("도감")) {
    override fun initialize(player: Player) {
        for (i in 0..53) {
            setItem(text(" "), null, Material.WHITE_STAINED_GLASS_PANE, 1, i)
        }

        setItem(
            text("아이템 도감"),
            null,
            Material.LAVA_BUCKET,
            1, 10,
            { _:InventoryClickEvent, _:Int ->
                forceCloseGUI()
                ItemDictionaryUI(player)
            })

        setItem(
            text("스킬 도감"),
            null,
            Material.IRON_SWORD,
            1, 12,
            { _:InventoryClickEvent, _:Int ->
                //스킬 도감 열기
            })

        setItem(
            text("직업 도감"),
            null,
            Material.LECTERN,
            1, 14,
            { _:InventoryClickEvent, _:Int ->
                //직업 도감 열기
            })

        setItem(
            text("닫기"),
        null,
            Material.BARRIER,
            1, 49,
            { _:InventoryClickEvent, _:Int ->
                forceCloseGUI()
            })

    }
}