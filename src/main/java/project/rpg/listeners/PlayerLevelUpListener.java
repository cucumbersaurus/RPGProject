package project.rpg.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.jetbrains.annotations.NotNull;

import static project.rpg.manager.ArrayManager.playerData_;
import static project.rpg.manager.FileManager.jsonFile_;

public class PlayerLevelUpListener implements Listener {

    @EventHandler
    public void onLevelUp(@NotNull PlayerLevelChangeEvent e) {

        String playerName;
        playerName = e.getPlayer().getName();

        if (e.getNewLevel() > e.getOldLevel()) {
            for (int i = 0; i < e.getNewLevel() - e.getOldLevel(); i++) {
                e.getPlayer().sendMessage(ChatColor.YELLOW + "Level Up!");
                playerData_.get(playerName).setLevel();
                jsonFile_.put(playerName, playerData_.get(playerName).getMap());
            }
        }
    }
}
