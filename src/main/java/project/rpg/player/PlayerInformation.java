package project.rpg.player;

import org.bukkit.entity.Player;
import project.rpg.manager.ArrayManager;
import project.rpg.player.info.Status;
import project.rpg.ui.ActionBarUI;

public class PlayerInformation {

    public static void makeInfo(Player player){
        player.setHealthScale(ArrayManager.playerData_.getOrDefault(player.getName(), new Status(player)).getHealth()/100.0);
        player.setHealth(ArrayManager.playerData_.getOrDefault(player.getName(), new Status(player)).getHealth()/100.0);

        new Status(player);
        ActionBarUI.addPlayer(player);
    }

}
