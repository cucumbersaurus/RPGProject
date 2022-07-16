package project.rpg.ui.inventory

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta

class MainMenu(player: Player) : GuiBase(player, 54, Component.text("메인 메뉴")) {
    override fun init(player: Player) {
        for (i in 0..53) {
            setItem(Component.text(" "), null, Material.WHITE_STAINED_GLASS_PANE, 1, i, Button.BACKGROUND._name, false)
        }
        setItem(Component.text("친구/파티"), null, Material.SKELETON_SKULL, 1, 10, Button.FRIENDS_PARTY._name, false)
        setItem(Component.text("퀘스트"), null, Material.MOJANG_BANNER_PATTERN, 1, 12, Button.QUESTS._name, false)
        setItem(Component.text("강화"), null, Material.ANVIL, 1, 14, Button.REINFORCE._name, false)
        setItem(Component.text("제작"), null, Material.CRAFTING_TABLE, 1, 16, Button.CRAFT._name, false)
        setItem(getPlayerHead(player), 28, Button.MY_PROFILE._name)
        setItem(Component.text("도감"), null, Material.KNOWLEDGE_BOOK, 1, 30, Button.DICTIONARY._name, false)
        setItem(Component.text("워프"), null, Material.NETHER_STAR, 1, 32, Button.WARP._name, false)
        setItem(Component.text("설정"), null, Material.FIREWORK_STAR, 1, 34, Button.SETTINGS._name, false)
        setItem(Component.text("닫기"), null, Material.BARRIER, 1, 49, Button.CLOSE._name, false)
    }

    private fun getPlayerHead(player: Player): ItemStack {
        val playerHead = ItemStack(Material.PLAYER_HEAD)
        val meta = playerHead.itemMeta as SkullMeta
        meta.owningPlayer = player
        meta.displayName(Component.text("내 프로필").color(TextColor.color(0xffff55)))
        playerHead.itemMeta = meta
        return playerHead
    }

    override fun onClick(event: InventoryClickEvent) {
        event.isCancelled = true
        val value = getValue(event.slot)
        when (Button.getFromName(value)) {
            Button.BACKGROUND -> {}
            Button.FRIENDS_PARTY -> {}
            Button.QUESTS -> {}
            Button.REINFORCE -> {}
            Button.CRAFT -> {}
            Button.MY_PROFILE -> {
                forceCloseGUI(event.whoClicked as Player)
                StatusMenu((event.whoClicked as Player))
            }
            Button.DICTIONARY -> {}
            Button.WARP -> {}
            Button.SETTINGS -> {}
            Button.CLOSE -> forceCloseGUI(event.whoClicked as Player)
            else -> {}
        }
    }

    private enum class Button(val _name: String) {
        BACKGROUND("background"), FRIENDS_PARTY("friends_party"), QUESTS("quests"), REINFORCE("reinforce"), CRAFT("craft"), MY_PROFILE(
            "my_profile"
        ),
        DICTIONARY("dictionary"), WARP("warp"), SETTINGS("settings"), CLOSE("close");

        companion object {
            fun getFromName(name: String): Button {
                return when (name) {
                    "background" -> BACKGROUND
                    "friends_party" -> FRIENDS_PARTY
                    "quests" -> QUESTS
                    "reinforce" -> REINFORCE
                    "craft" -> CRAFT
                    "my_profile" -> MY_PROFILE
                    "dictionary" -> DICTIONARY
                    "warp" -> WARP
                    "settings" -> SETTINGS
                    "close" -> CLOSE
                    else -> throw IllegalStateException("Unexpected value: $name")
                }
            }
        }
    }
}