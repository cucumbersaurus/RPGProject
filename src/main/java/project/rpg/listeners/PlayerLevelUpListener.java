package project.rpg.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.jetbrains.annotations.NotNull;

import static project.rpg.manager.ArrayManager.playerData;
import static project.rpg.manager.FileManager.jsonFile;

public class PlayerLevelUpListener implements Listener {

    @EventHandler
    public void onLevelUp(@NotNull PlayerLevelChangeEvent e) {

        String playerName;
        playerName = e.getPlayer().getName();

        if (e.getNewLevel() > e.getOldLevel()) {
            for (int i = 0; i < e.getNewLevel() - e.getOldLevel(); i++) {
                e.getPlayer().sendMessage(ChatColor.YELLOW + "Level Up!");
                playerData.get(playerName).setLevel();
                jsonFile.put(playerName, playerData.get(playerName).getMap());
            }
        }
    }
}
