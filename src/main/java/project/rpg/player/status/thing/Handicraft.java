package project.rpg.player.status.thing;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import project.rpg.player.status.base.StatusBase;
import project.rpg.player.status.base.StatusName;

public class Handicraft extends StatusBase{   //손재주

    public Handicraft() {
        super(StatusName.HANDICRAFT.getName());
    }

    @Override
    public void effect(Player player) {
    }

}
