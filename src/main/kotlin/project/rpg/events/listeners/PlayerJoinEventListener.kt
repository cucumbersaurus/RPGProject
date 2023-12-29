package project.rpg.events.listeners

import net.kyori.adventure.text.Component.text
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import project.rpg.player.PlayerInformation
import project.rpg.textComponets.color.TextColors

class PlayerJoinEventListener : Listener {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        for (p in Bukkit.getOnlinePlayers()) {
            player.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.6f, 1f)
        }
        PlayerInformation.makeInfo(player)
        event.joinMessage(
            text("앗 야생의 ").color(TextColors.GREEN_YELLOW.color)
                .append(text("『").color(TextColors.MEDIUM_TURQUOISE.color))
                .append(text(player.name).color(TextColors.ORANGE.color))
                .append(text("』").color(TextColors.MEDIUM_TURQUOISE.color))
                .append(text("(이)가 들어왔다!").color(TextColors.GREEN_YELLOW.color))
        )
        //FileManager.getFile(player)
    }
}