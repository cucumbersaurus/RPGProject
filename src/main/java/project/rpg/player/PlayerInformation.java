package project.rpg.player;

import org.bukkit.entity.Player;
import project.rpg.player.info.Status;
import project.rpg.ui.ActionBarUI;

public class PlayerInformation {


    public static void makeInfo(Player player){
        new Status(player);
        ActionBarUI.addPlayer(player);

    }


}
