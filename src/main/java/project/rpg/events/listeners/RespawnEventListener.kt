package project.rpg.events.listeners

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerRespawnEvent
import project.rpg.Rpg
import project.rpg.player.User
import project.rpg.player.status.base.StatusName

class RespawnEventListener(private val _plugin: Rpg) : Listener {
    @EventHandler //리스폰시 체력이 최대체력만큼 설정되지 않는 오류를 고치기 위한 코드
    fun onPlayerRespawn(event: PlayerRespawnEvent) { //스케줄러를 사용한 이유는 메인 스레드에서 하면 작동이 안됨
        val player = event.player
        Bukkit.getScheduler().scheduleSyncDelayedTask(_plugin, {
            player.healthScale = User.getPlayer(player).status.getStatusValues(StatusName.HEALTH) / 10.0
            player.isHealthScaled = true
            player.health = User.getPlayer(player).status.getStatusValues(StatusName.HEALTH) / 10.0
            _plugin.actionBar.updateActionBar()
        }, 0)
    }
}