package project.rpg.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;
import project.rpg.player.PlayerInformation;


public class PlayerJoinEventListener implements Listener {

    @EventHandler
    public void onJoin(@NotNull PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerInformation.makeInfo(player);


        event.setJoinMessage((ChatColor.YELLOW + "앗! 야생의 ") + (ChatColor.GREEN + player.getName()) + (ChatColor.YELLOW + "(이)가 들어왔다!"));
    }
}
