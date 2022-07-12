package project.rpg.ui

import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import project.rpg.Rpg
import project.rpg.player.User

class ActionBarUI(private val _plugin: Rpg) {
    private val showActionBar = Runnable {
        for (player in players) {
            sendActionBarToPlayer(player)
        }
    }

    fun startActionBar() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(_plugin, showActionBar, 20, 10)
    }

    fun updateActionBar() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(_plugin, showActionBar, 0)
    }

    fun updateActionBar(player: Player) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(_plugin, {
            sendActionBarToPlayer(player)
        }, 0)
    }

    private fun sendActionBarToPlayer(player: Player){
        val mana = User.getPlayer(player).mana
        val message = text()
        message.append(
        text().color(TextColor.color(0xff5555)).
        content( "체력 : " +
                String.format("%.2f", player.health * 100) + "/" +
                String.format("%.2f", player.healthScale * 100
        )))
        message.append(
        text().color(TextColor.color(0x5555ff)).
        content( "          마나 : " + mana.mana + "/" + mana.maxMana))
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