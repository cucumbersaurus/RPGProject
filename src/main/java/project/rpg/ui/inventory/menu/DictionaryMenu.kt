package project.rpg.ui.inventory.menu

import net.kyori.adventure.text.Component.text
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import project.rpg.ui.inventory.GuiBase
import project.rpg.ui.inventory.menu.list.dictionary.ItemDictionaryUI

class DictionaryMenu(player: Player) : GuiBase(player, text("도감")) {
    override fun initialize(player: Player) {
        fillBackGround()

        setItem(
            Material.LAVA_BUCKET,
            10,
            { _:InventoryClickEvent, _:Int ->
                forceCloseGUI()
                ItemDictionaryUI(player)
            },
            text("아이템 도감")
        )

        setItem(
            Material.IRON_SWORD,
            12,
            { _:InventoryClickEvent, _:Int ->
                //스킬 도감 열기
            },
            text("스킬 도감")
        )

        setItem(
            Material.LECTERN,
            14,
            { _:InventoryClickEvent, _:Int ->
                //직업 도감 열기
            },
            text("직업 도감"))

        setItem(
            Material.BARRIER,
            49,
            { _:InventoryClickEvent, _:Int ->
                forceCloseGUI()
            },
            text("닫기"))
    }
}