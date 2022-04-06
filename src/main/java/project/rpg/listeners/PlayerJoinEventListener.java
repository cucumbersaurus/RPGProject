package project.rpg.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;
import project.rpg.manager.AttributeManager;
import project.rpg.player.info.Status;

import static project.rpg.manager.ArrayManager.Players;
import static project.rpg.manager.ArrayManager.playerData;
import static project.rpg.manager.FileManager.jsonFile;
import static project.rpg.manager.FileManager.jsonList;

public class PlayerJoinEventListener implements Listener {

    @EventHandler
    public void onJoin(@NotNull PlayerJoinEvent e) {
        String playerName;
        playerName = e.getPlayer().getName();
        e.setJoinMessage((ChatColor.YELLOW + "앗! 야생의 ") + (ChatColor.GREEN + playerName) + (ChatColor.YELLOW + "(이)가 들어왔다!"));
        boolean contains = Players.contains(playerName);
        if (contains) {
            if (playerData.get(playerName) == null) {
                Status status = new Status(playerName);
                playerData.put(playerName, status);

                jsonFile.put(playerName, status.getMap());
            }
            AttributeManager.setAttributes(e.getPlayer(), playerData.get(playerName));
        } else {
            Status status = new Status(playerName);
            Players.add(playerName);
            playerData.put(playerName, status);

            jsonList.add(playerName);
            jsonFile.put(playerName, status.getMap());
            AttributeManager.setAttributes(e.getPlayer(), playerData.get(playerName));
        }
    }

}
