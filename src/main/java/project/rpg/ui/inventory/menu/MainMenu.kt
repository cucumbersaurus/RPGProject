package project.rpg.ui.inventory.menu

import net.kyori.adventure.text.Component.text
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import project.rpg.textComponets.color.DefaultTextColors
import project.rpg.ui.inventory.GuiBase

class MainMenu(player: Player) : GuiBase(player, 54, text("메인 메뉴")) {
    override fun initialize(player: Player) {
        for (i in 0..53) {
            setItem(text(" "), null, Material.WHITE_STAINED_GLASS_PANE, 1, i, Button.BACKGROUND.name, false)
        }
        setItem(text("친구/파티"), null, Material.SKELETON_SKULL, 1, 10, Button.FRIENDS_PARTY.name, false)
        setItem(text("퀘스트"), null, Material.MOJANG_BANNER_PATTERN, 1, 12, Button.QUESTS.name, false)
        setItem(text("강화"), null, Material.ANVIL, 1, 14, Button.REINFORCE.name, false)
        setItem(text("제작"), null, Material.CRAFTING_TABLE, 1, 16, Button.CRAFT.name, false)
        setItem(getPlayerHead(player), 28, Button.MY_PROFILE.name)
        setItem(text("도감"), null, Material.KNOWLEDGE_BOOK, 1, 30, Button.DICTIONARY.name, false)
        setItem(text("워프"), null, Material.NETHER_STAR, 1, 32, Button.WARP.name, false)
        setItem(text("설정"), null, Material.FIREWORK_STAR, 1, 34, Button.SETTINGS.name, false)
        setItem(text("닫기"), null, Material.BARRIER, 1, 49, Button.CLOSE.name, false)
    }

    private fun getPlayerHead(player: Player): ItemStack {
        val playerHead = ItemStack(Material.PLAYER_HEAD)
        val meta = playerHead.itemMeta as SkullMeta
        meta.owningPlayer = player
        meta.displayName(text("내 프로필").color(DefaultTextColors.YELLOW.color))
        playerHead.itemMeta = meta
        return playerHead
    }

    override fun onClick(event: InventoryClickEvent) {
        event.isCancelled = true
        val player = event.whoClicked as Player
        when (getValue(event.slot)) {
            Button.BACKGROUND.name -> {return}
            Button.FRIENDS_PARTY.name -> {}
            Button.QUESTS.name -> {}
            Button.REINFORCE.name -> {}
            Button.CRAFT.name -> {}
            Button.MY_PROFILE.name -> {
                forceCloseGUI()
                StatusMenu((player))
            }
            Button.DICTIONARY.name -> {
                forceCloseGUI()
                DictionaryMenu(event.whoClicked as Player)
            }
            Button.WARP.name -> {}
            Button.SETTINGS.name -> {}
            Button.CLOSE.name -> forceCloseGUI()
        }
    }

    private enum class Button {
        BACKGROUND,
        FRIENDS_PARTY,
        QUESTS,
        REINFORCE,
        CRAFT,
        MY_PROFILE,
        DICTIONARY,
        WARP,
        SETTINGS,
        CLOSE,
    }
}