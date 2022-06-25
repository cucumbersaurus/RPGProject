package project.rpg.events.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent
import project.rpg.player.PlayerInformation

class PlayerQuitEventListener : Listener {
    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        PlayerInformation.deleteInfo(event.player)
    }
}