package project.rpg.listeners

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import project.rpg.player.PlayerInformation

class PlayerJoinEventListener : Listener {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        PlayerInformation.makeInfo(player)
        event.joinMessage(
            Component.text("앗 야생의 ").color(TextColor.color(0x55ff55))
                .append(Component.text(player.name).color(TextColor.color(0xffff55)))
                .append(Component.text("(이)가 들어왔다!")).color(TextColor.color(0x55ff55))
        )
    }
}