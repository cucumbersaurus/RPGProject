package project.rpg.player;

import org.bukkit.entity.Player;
import project.rpg.manager.AttributeManager;
import project.rpg.player.info.Mana;
import project.rpg.player.info.Skill;
import project.rpg.player.info.Status;
import project.rpg.ui.ActionBarUI;

public class PlayerInformation {


    public static void makeInfo(Player player){
        ActionBarUI.addPlayer(player);
        AttributeManager.setAttributes(player, new Status(player));

        player.setHealthScale(Status.getPlayerMap().get(player.getUniqueId()).getHealth()/100.0);
        player.setHealthScaled(true);
        player.setHealth(Status.getPlayerMap().get(player.getUniqueId()).getHealth()/100.0);

        Mana.addPlayer(player);

        new Skill(player, null);
    }

    public static void deleteInfo(Player player) {
        ActionBarUI.deletePlayer(player);
    }

    public PlayerInformation(){

    }

}
