package project.rpg.ui.inventory.menu

import net.kyori.adventure.text.Component.text
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import project.rpg.textComponets.color.DefaultTextColors
import project.rpg.ui.inventory.GuiBase

class MainMenu(player: Player) : GuiBase(player,  text("메인 메뉴")) {
    override fun initialize() {
        fillBackGround()
        setItem(
            Material.SKELETON_SKULL,
            10,
            null,
            text("친구/파티")
        )
        setItem(
            Material.MOJANG_BANNER_PATTERN,
            12,
            null,
            text("퀘스트")
        )
        setItem(
            Material.ANVIL,
            14,
            null,
            text("강화"),
        )
        setItem(
            Material.CRAFTING_TABLE,
            16,
            null,
            text("제작"),
        )
        setItem(
            getPlayerHead(player),
            28
        ) { event: InventoryClickEvent, _: Int ->
            event.isCancelled = true
            forceCloseGUI()
            StatusMenu(player)
        }
        setItem(
            Material.KNOWLEDGE_BOOK,
            30,
            { event:InventoryClickEvent, _: Int ->
                event.isCancelled = true
                forceCloseGUI()
                DictionaryMenu(player)
            },
            text("도감"),
        )
        setItem(
            Material.NETHER_STAR,
            32,
            null,
            text("워프"),
        )
        setItem(
            Material.FIREWORK_STAR,
            34,
            null,
            text("설정")
        )
        setItem(
            Material.BARRIER,
            49,
            {event:InventoryClickEvent, _:Int ->
                event.isCancelled = true
                forceCloseGUI()
            },
            text("닫기")
        )
    }

    private fun getPlayerHead(player: Player): ItemStack {
        val playerHead = ItemStack(Material.PLAYER_HEAD)
        val meta = playerHead.itemMeta as SkullMeta
        meta.owningPlayer = player
        meta.displayName(text("내 프로필").color(DefaultTextColors.YELLOW.color))
        playerHead.itemMeta = meta
        return playerHead
    }
}