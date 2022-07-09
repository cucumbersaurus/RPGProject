package project.rpg.player.status.thing;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import project.rpg.player.status.base.StatusBase;
import project.rpg.player.status.base.StatusName;

public class Luck extends StatusBase {   //행운

    public Luck() {
        super(StatusName.LUCK.getName());
    }

    @Override
    public void effect(Player player) {
    }

}
