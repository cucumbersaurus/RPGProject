package project.rpg.ui

import net.md_5.bungee.api.ChatColor
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import project.rpg.Rpg
import project.rpg.player.info.Mana

class ActionBarUI(private val _plugin: Rpg) {
    var showActionBar = Runnable {
        for (player in _players) {
            var message = "체력 : " + String.format("%.2f", player.health * 100) + "/" + String.format(
                "%.2f",
                player.healthScale * 100
            )
            message += ChatColor.BLUE.toString() + "          마나 : " + Mana.getMana(player) + "/" + Mana.getMaxMana(
                player
            )
            player.sendActionBar(ChatColor.RED.toString() + message)
        }
    }

    fun startActionBar() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(_plugin, showActionBar, 0, 10)
    }

    fun updateActionBar() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(_plugin, showActionBar, 0)
    }

    fun updateActionBar(player: Player) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(_plugin, {
            var message = "체력 : " + String.format("%.2f", player.health * 100) + "/" + String.format(
                "%.2f",
                player.healthScale * 100
            )
            message += ChatColor.BLUE.toString() + "          마나 : " + Mana.getMana(player) + "/" + Mana.getMaxMana(
                player
            )
            player.sendActionBar(ChatColor.RED.toString() + message)
        }, 0)
    }

    companion object {
        protected val _players: MutableList<Player> = ArrayList()
        fun addPlayer(player: Player) {
            _players.add(player)
        }

        fun deletePlayer(player: Player) {
            _players.remove(player)
        }
    }
}