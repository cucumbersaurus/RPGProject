package project.rpg.ui.inventory.menu

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.text
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import project.rpg.extensions.*
import project.rpg.player.User
import project.rpg.player.status.Status
import project.rpg.textComponets.color.DefaultTextColors
import project.rpg.ui.inventory.GuiBase

class StatusMenu(player: Player) : GuiBase(player, text("스텟 메뉴")) {
    private lateinit var user: User
    private lateinit var status: Status
    override fun initialize() {
        user = User.getPlayer(player)!!
        status = user.status

        fillBackGround(Material.LIGHT_GRAY_STAINED_GLASS_PANE)
        for (i in 0..8) {
            setItem(Material.BLACK_STAINED_GLASS_PANE, i)

        }
        for (i in 9..44 step 9) {
            setItem(Material.BLACK_STAINED_GLASS_PANE, i)

        }
        for (i in 8..44 step 9) {
            setItem(Material.BLACK_STAINED_GLASS_PANE, i)
        }

        setItem(
            Material.IRON_AXE,
            12,
            { event: InventoryClickEvent, _: Int ->
                event.isCancelled = true
                status.addStrength()
                reloadUi()
            },
            text("힘 strength"),
            listOf(text(status.strength)),
            1,
            true
        )
        setItem(
            Material.FEATHER,
            14,
            { event: InventoryClickEvent, _: Int ->
                event.isCancelled = true
                status.addSpeed()
                reloadUi()
            },
            text("신속 speed"),
            listOf(text(status.speed)),
            1,
            true
        )
        setItem(
            Material.IRON_SWORD,
            20,
            { event: InventoryClickEvent, _: Int ->
                event.isCancelled = true
                status.addAgility()
                reloadUi()
            },
            text("민첩 agility"),
            listOf(text(status.agility)),
            1,
            true
        )
        setItem(
            Material.GOLDEN_APPLE,
            24,
            { event: InventoryClickEvent, _: Int ->
                event.isCancelled = true
                status.addMaxHealth()
                reloadUi()
            },
            text("체력 health"),
            listOf(text(status.maxHealth)),
            1,
            true
        )
        setItem(
            Material.IRON_CHESTPLATE,
            38,
            { event: InventoryClickEvent, _: Int ->
                event.isCancelled = true
                status.addDefense()
                reloadUi()
            },
            text("방어 defense"),
            listOf(text(status.defense)),
            1,
            true
        )
        setItem(
            Material.DIAMOND,
            42,
            { event: InventoryClickEvent, _: Int ->
                event.isCancelled = true
                status.addLuck()
                reloadUi()
            },
            text("행운 luck"),
            listOf(text(status.luck)),
            1,
            true
        )
        setItem(
            Material.IRON_PICKAXE,
            48,
            { event: InventoryClickEvent, _: Int ->
                event.isCancelled = true
                status.addHandicraft()
                reloadUi()
            },
            text("손재주 hadicraft"),
            listOf(text(status.handicraft)),
            1,
            true
        )
        setItem(
            Material.BOOK,
            50,
            { event: InventoryClickEvent, _: Int ->
                event.isCancelled = true
                status.addIntelligence()
                reloadUi()
            },
            text("마력 intelligence"),
            listOf(text(status.intelligence)),
            1,
            true
        )
        setItem(
            getPlayerHead(),
            31
        )
        setItem(
            Material.BEACON,
            45,
            { event: InventoryClickEvent, _: Int ->
                event.isCancelled = true
                reloadUi()
            },
            text("페이지 새로고침"),
            listOf(text("눌러서 새로고침")),
        )
        setItem(
            Material.BARRIER,
            53,
            { event: InventoryClickEvent, _: Int ->
                event.isCancelled = true
                forceCloseGUI()
            },
            text("닫기"),
            listOf(text("눌러서 페이지 닫기"))
        )
    }

    private fun reloadUi() {
        player.playSound(player.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1f)
        user.status.reloadMap()
        initialize()
    }

    private fun getPlayerHead(): ItemStack {
        val playerHead = ItemStack(Material.PLAYER_HEAD)
        val meta = playerHead.itemMeta as SkullMeta
        meta.owningPlayer = player

        meta.displayName(text("정보 info").color(DefaultTextColors.YELLOW))
        meta.lore(
            listOf(
                text("================"),
                text(user.title.name),
                text(user.levels.level.toString() + ".lv"),
                text(user.levels.exp.toString() + " / " + user.levels.needForNextLev),
                text(" "),
                text(" "),
                text("hp : " + status.maxHealth),
                text("strength : " + status.strength),
                text("agility : " + status.agility),
                text("defense : " + status.defense),
                text("speed : " + status.speed),
                text("luck : " + status.luck),
                text("intelligence : " + status.intelligence),
                text("handicraft : " + status.handicraft),
                text(" "),
                text("more : " + status.additionalStatusPoint),
                text("================")

            ) as List<Component>
        )
        playerHead.itemMeta = meta
        return playerHead
    }
}