package project.rpg.ui.text

import io.github.monun.heartbeat.coroutines.HeartbeatScope
import io.github.monun.heartbeat.coroutines.Suspension
import kotlinx.coroutines.launch
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import project.rpg.Rpg
import project.rpg.extensions.mana

class ActionBarUI(private val _plugin: Rpg) {
    private val showActionBar = Runnable {
        for (player in players) {
            sendActionBarToPlayer(player)
        }
    }

    fun startCoroutineActionBar() {
        Thread.sleep(10)
        HeartbeatScope().launch {
            val suspension = Suspension()
            while (true) {
                suspension.delay(50 * 20L)

                for (player in Bukkit.getOnlinePlayers()) {
                    sendActionBarToPlayer(player)
                }
            }
        }
    }

    fun updateActionBar() {
        HeartbeatScope().launch {
            for (player in Bukkit.getOnlinePlayers()) {
                sendActionBarToPlayer(player)
            }
        }
    }

    fun updateActionBar(player: Player) {
        HeartbeatScope().launch {
            sendActionBarToPlayer(player)
        }
    }

    /**
     * @param player 액션바를 보낼 플레이어
     * @re
     */
    private fun sendActionBarToPlayer(player: Player) {
        val mana = player.mana
        val message = text()
        message.append(
            text().color(TextColor.color(0xff5555)).content(
                "체력 : " +
                        String.format("%.2f", player.health * 100) + "/" +
                        String.format(
                            "%.2f", player.healthScale * 100
                        )
            )
        )
        message.append(
            text().color(TextColor.color(0x5555ff)).content("          마나 : " + mana.mana + "/" + mana.maxMana)
        )
        player.sendActionBar(message.build())

    }

    companion object {
        private val players: MutableList<Player> = ArrayList()
        fun addPlayer(player: Player) {
            players.add(player)
        }

        fun deletePlayer(player: Player) {
            players.remove(player)
        }
    }
}