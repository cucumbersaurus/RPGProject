package project.rpg.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.jetbrains.annotations.NotNull;
import project.rpg.player.info.Status;

import static project.rpg.manager.FileManager.jsonFile_;

public class PlayerLevelUpListener implements Listener {

    @EventHandler
    public void onLevelUp(@NotNull PlayerLevelChangeEvent e) {

        Player player;
        player = e.getPlayer();

        if (e.getNewLevel() > e.getOldLevel()) {
            for (int i = 0; i < e.getNewLevel() - e.getOldLevel(); i++) {
                e.getPlayer().sendMessage(ChatColor.YELLOW + "Level Up!");
                Status.getPlayerMap().get(player).setLevel();
                jsonFile_.put(player.getName(), Status.getPlayerMap().get(player).getMap());
            }
        }
    }
}
