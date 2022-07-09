package project.rpg.player.status.thing;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import project.rpg.player.status.base.StatusBase;
import project.rpg.player.status.base.StatusName;

public class Speed extends StatusBase {   //발 빠르기

    public Speed() {
        super(StatusName.SPEED);
    }

    @Override
    public void effect(Player player) {
        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1d+(this._value-10d)/1000d);
    }

}
