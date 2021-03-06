package project.rpg.ui.inventory.menu

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import project.rpg.player.User
import project.rpg.player.status.Status
import project.rpg.player.status.base.StatusName
import project.rpg.ui.inventory.GuiBase

class StatusMenu(player: Player) : GuiBase(player, 54, Component.text("스텟 메뉴")) {
    private var _user: User? = null
    private var _status: Status? = null
    private var _player: Player? = null
    override fun init(player: Player) {
        _player = player
        _user = User.getPlayer(player)
        _status = _user!!.status
        for (i in 0..53) {
            setItem(Component.text(" "), null, Material.LIGHT_GRAY_STAINED_GLASS_PANE, 1, i, "stats.background", false)
        }
        for (i in 0..8) {
            setItem(Component.text(" "), null, Material.BLACK_STAINED_GLASS_PANE, 1, i, "stats.background", false)
        }
        run {
            var i = 9
            while (i < 45) {
                setItem(Component.text(" "), null, Material.BLACK_STAINED_GLASS_PANE, 1, i, "stats.background", false)
                i += 9
            }
        }
        var i = 8
        while (i < 45) {
            setItem(Component.text(" "), null, Material.BLACK_STAINED_GLASS_PANE, 1, i, "stats.background", false)
            i += 9
        }
        setItem(
            Component.text("힘 strength"),
            ArrayList(listOf(Component.text(_status!!.getStatusValues(StatusName.STRENGTH)))),
            Material.IRON_AXE,
            1,
            12,
            "stats.strength",
            true
        )
        setItem(
            Component.text("신속 speed"),
            ArrayList(listOf(Component.text(_status!!.getStatusValues(StatusName.SPEED)))),
            Material.FEATHER,
            1,
            14,
            "stats.speed",
            true
        )
        setItem(
            Component.text("민첩 agility"),
            ArrayList(listOf(Component.text(_status!!.getStatusValues(StatusName.AGILITY)))),
            Material.IRON_SWORD,
            1,
            20,
            "stats.agility",
            true
        )
        setItem(
            Component.text("체력 health"),
            ArrayList(listOf(Component.text(_status!!.getStatusValues(StatusName.HEALTH)))),
            Material.GOLDEN_APPLE,
            1,
            24,
            "stats.health",
            true
        )
        setItem(
            Component.text("방어 defense"),
            ArrayList(listOf(Component.text(_status!!.getStatusValues(StatusName.DEFENSE)))),
            Material.IRON_CHESTPLATE,
            1,
            38,
            "stats.defense",
            true
        )
        setItem(
            Component.text("행운 luck"),
            ArrayList(listOf(Component.text(_status!!.getStatusValues(StatusName.LUCK)))),
            Material.DIAMOND,
            1,
            42,
            "stats.luck",
            true
        )
        setItem(
            Component.text("손재주 handicraft"),
            ArrayList(listOf(Component.text(_status!!.getStatusValues(StatusName.HANDICRAFT)))),
            Material.IRON_PICKAXE,
            1,
            48,
            "stats.handicraft",
            true
        )
        setItem(
            Component.text("마력 intelligence"),
            ArrayList(listOf(Component.text(_status!!.getStatusValues(StatusName.INTELLIGENCE)))),
            Material.BOOK,
            1,
            50,
            "stats.intelligence",
            true
        )
        setItem(
            getPlayerHead(player),
            31,
            "stats.info"
        )
        setItem(
            Component.text("현재 페이지/새로고침"),
            ArrayList(listOf(Component.text("현재 페이지를 나타냅니다."), Component.text("눌러서 새로고침"))),
            Material.BEACON,
            1,
            45,
            "stats.reload",
            false
        )
        setItem(
            Component.text("현재 페이지/닫기"),
            ArrayList(listOf(Component.text("페이지 닫기"))),
            Material.BARRIER,
            1,
            53,
            "stats.close",
            false
        )
    }

    override fun onClick(event: InventoryClickEvent) {
        event.isCancelled = true
        val btn = getValue(event.slot) ?: return
        when (btn) {
            "stats.strength" -> {
                _status!!.addStatus(StatusName.STRENGTH, 1)
                reloadUi()
            }
            "stats.health" -> {
                _status!!.addStatus(StatusName.HEALTH, 1)
                reloadUi()
            }
            "stats.agility" -> {
                _status!!.addStatus(StatusName.AGILITY, 1)
                reloadUi()
            }
            "stats.speed" -> {
                _status!!.addStatus(StatusName.SPEED, 1)
                reloadUi()
            }
            "stats.luck" -> {
                _status!!.addStatus(StatusName.LUCK, 1)
                reloadUi()
            }
            "stats.defense" -> {
                _status!!.addStatus(StatusName.DEFENSE, 1)
                reloadUi()
            }
            "stats.handicraft" -> {
                _status!!.addStatus(StatusName.HANDICRAFT, 1)
                reloadUi()
            }
            "stats.intelligence" -> {
                _status!!.addStatus(StatusName.INTELLIGENCE, 1)
                reloadUi()
            }
            "stats.reload" -> reloadUi()
            "stats.close" -> forceCloseGUI(_player!!)
            else -> {}
        }
    }

    private fun reloadUi() {
        _player!!.playSound(_player!!.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1f)
        _user!!.status.reloadMap()
        init(_player!!)
    }

    private fun getPlayerHead(player: Player): ItemStack {
        val playerHead = ItemStack(Material.PLAYER_HEAD)
        val meta = playerHead.itemMeta as SkullMeta
        meta.owningPlayer = player
        meta.displayName(Component.text("정보 info").color(TextColor.color(0xffff55)))
        meta.lore(
            listOf(
                Component.text("================"),
                Component.text(_user!!.name.name),
                Component.text(_user!!.level.level.toString() + ".lv"),
                Component.text(_user!!.level.exp.toString() + " / " + _user!!.level.needForNextLev),
                Component.text(" "),
                Component.text(" "),
                Component.text("hp : " + _status!!.getStatusValues(StatusName.HEALTH)),
                Component.text("strength : " + _status!!.getStatusValues(StatusName.STRENGTH)),
                Component.text("agility : " + _status!!.getStatusValues(StatusName.AGILITY)),
                Component.text("defense : " + _status!!.getStatusValues(StatusName.DEFENSE)),
                Component.text("speed : " + _status!!.getStatusValues(StatusName.SPEED)),
                Component.text("luck : " + _status!!.getStatusValues(StatusName.LUCK)),
                Component.text("intelligence : " + _status!!.getStatusValues(StatusName.INTELLIGENCE)),
                Component.text("handicraft : " + _status!!.getStatusValues(StatusName.HANDICRAFT)),
                Component.text(" "),
                Component.text("more : " + _status!!.additionalStatusPoint),
                Component.text("================")

            ) as List<Component>?
        )
        playerHead.itemMeta = meta
        return playerHead
    }
}