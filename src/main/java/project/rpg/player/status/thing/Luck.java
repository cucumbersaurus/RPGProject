package project.rpg.player.status.thing;

import org.bukkit.entity.Player;
import project.rpg.player.status.base.StatusBase;
import project.rpg.player.status.base.StatusName;

public class Luck extends StatusBase {   //행운

    public Luck() {
        super(StatusName.LUCK);
    }

    @Override
    public void effect(Player player) {
    }

}
