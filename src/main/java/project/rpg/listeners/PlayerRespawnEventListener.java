package project.rpg.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import project.rpg.Rpg;
import project.rpg.manager.ArrayManager;
import project.rpg.player.info.Status;

public class PlayerRespawnEventListener implements Listener {

    private Rpg _plugin;

    public PlayerRespawnEventListener(Rpg plugin){
        _plugin = plugin;
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event){
        Player player = event.getPlayer();

        Bukkit.getScheduler().scheduleSyncDelayedTask(_plugin, new Runnable() {
            @Override
            public void run() {
                player.setHealthScale(ArrayManager.playerData_.getOrDefault(player.getName(), new Status(player)).getHealth()/100.0);
                //player.setHealthScaled(true);
                player.setHealth(ArrayManager.playerData_.getOrDefault(player.getName(), new Status(player)).getHealth()/100.0);
                _plugin.actionBar.updateActionBar();
                //Bukkit.broadcastMessage("asdfasdsdfasfd");
            }
        }, 1);
    }

}
