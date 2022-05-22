package project.rpg.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;
import project.rpg.manager.AttributeManager;
import project.rpg.player.PlayerInformation;
import project.rpg.player.info.Status;

import static project.rpg.manager.ArrayManager.Players_;
import static project.rpg.manager.ArrayManager.playerData_;
import static project.rpg.manager.FileManager.jsonFile_;
import static project.rpg.manager.FileManager.jsonList_;

public class PlayerJoinEventListener implements Listener {

    @EventHandler
    public void onJoin(@NotNull PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        PlayerInformation.makeInfo(player);

        event.setJoinMessage((ChatColor.YELLOW + "앗! 야생의 ") + (ChatColor.GREEN + playerName) + (ChatColor.YELLOW + "(이)가 들어왔다!"));


        if (Players_.contains(playerName)) {

            if (playerData_.get(playerName) == null) {

                Status status = new Status(event.getPlayer());

                playerData_.put(playerName, status);
                jsonFile_.put(playerName, status.getMap());
            }
            AttributeManager.setAttributes(event.getPlayer(), playerData_.get(playerName));
        } else {
            Status status = new Status(player);

            Players_.add(playerName);
            playerData_.put(playerName, status);

            jsonList_.add(playerName);
            jsonFile_.put(playerName, status.getMap());

            AttributeManager.setAttributes(event.getPlayer(), playerData_.get(playerName));
        }

    }

}
