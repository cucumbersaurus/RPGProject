package project.rpg.events.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import project.rpg.Rpg;
import project.rpg.player.Human;
import project.rpg.player.status.base.StatusName;

public class RespawnEventListener implements Listener {

    private final Rpg _plugin;

    public RespawnEventListener(Rpg plugin){
        _plugin = plugin;
    }

    @EventHandler//리스폰시 체력이 최대체력만큼 설정되지 않는 오류를 고치기 위한 코드
    public void onPlayerRespawn(PlayerRespawnEvent event){//스케줄러를 사용한 이유는 메인 스레드에서 하면 작동이 안됨

        Player player = event.getPlayer();

        Bukkit.getScheduler().scheduleSyncDelayedTask(_plugin, () -> {
            player.setHealthScale(Human.getPlayer(player).getStats().getStatus(StatusName.HEALTH.getName())/100.0);
            player.setHealthScaled(true);
            player.setHealth(Human.getPlayer(player).getStats().getStatus(StatusName.HEALTH.getName())/100.0);
            _plugin.actionBar.updateActionBar();
        }, 0);
    }

}
