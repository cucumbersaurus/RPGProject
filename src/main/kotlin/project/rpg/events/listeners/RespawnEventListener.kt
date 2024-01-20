package project.rpg.events.listeners

import io.github.monun.heartbeat.coroutines.HeartbeatScope
import kotlinx.coroutines.launch
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerRespawnEvent
import project.rpg.Rpg
import project.rpg.player.User
import project.rpg.player.status.base.StatusName

class RespawnEventListener() : Listener {
    @EventHandler //리스폰시 체력이 최대체력만큼 설정되지 않는 오류를 고치기 위한 코드
    fun onPlayerRespawn(event: PlayerRespawnEvent) { //스케줄러를 사용한 이유는 메인 스레드에서 하면 작동이 안됨
        val player = event.player
        HeartbeatScope().launch {
            player.healthScale = User.getPlayer(player)!!.status.getStatusValues(StatusName.HEALTH) / 10.0
            player.isHealthScaled = true
            player.health = User.getPlayer(player)!!.status.getStatusValues(StatusName.HEALTH) / 10.0
            Rpg.actionBar.updateActionBar()
        }
    }
}