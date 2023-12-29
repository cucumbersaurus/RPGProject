package project.rpg.ui.inventory.interection.block

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.text
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import project.rpg.ui.inventory.GuiBase
import java.util.*

class EnforceUI(player: Player) : GuiBase(player, text("메인 메뉴"), 54) {
    override fun initialize() {
        fillBackGround()
        setItem(
            Material.ANVIL,
            31,
            null,
            text("강화하기"),
        )
    }

    private fun enforce(level: Short, player: Player, event: InventoryClickEvent) {
        val result = Random().nextInt(10000)
        val success: Int
        val fail: Int
        when (level.toInt()) {
            0 -> {
                success = 9000
                fail = 900
            }

            1 -> {
                success = 8000
                fail = 1500
            }

            2 -> {
                success = 6500
                fail = 2000
            }

            3 -> {
                success = 4500
                fail = 3000
            }

            4 -> {
                success = 3000
                fail = 3500
            }

            5 -> {
                success = 1000
                fail = 4000
            }

            6 -> {
                success = 500
                fail = 3000
            }

            7 -> {
                success = 100
                fail = 1900
            }

            8 -> {
                success = 50
                fail = 1500
            }

            9 -> {
                success = 1
                fail = 1000
            }

            else -> {
                success = -1
                fail = -1
            }
        }
        if (success == -1) {
            onNotAble(player, event)
        } else {
            if (result <= success) {
                onSuccess(player, event)
            } else if (result <= success + fail) {
                onFail(player, event)
            } else {
                onBreak(player, event)
            }
        }
    }

    private fun onNotAble(player: Player, event: InventoryClickEvent) {  //풀강이거나 강화못하는 아이템
        player.playSound(player, Sound.BLOCK_ANVIL_BREAK, 0.6f, 1f)
        event.isCancelled = true
    }

    private fun onSuccess(player: Player, event: InventoryClickEvent) {  //성공시  강화단계 +1
        player.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.6f, 1f)
        val item = event.currentItem
        val lore = item!!.lore()
        TODO("not implemented")
    }

    private fun onFail(player: Player, event: InventoryClickEvent) { //실패시   강화단계 -1, 0이면 파괴
        TODO("not implemented")
    }

    private fun onBreak(player: Player, event: InventoryClickEvent) {  //파괴시  아이템 삭제
        player.playSound(player, Sound.BLOCK_ANVIL_DESTROY, 0.6f, 1f)
        TODO("not implemented")
    }
}