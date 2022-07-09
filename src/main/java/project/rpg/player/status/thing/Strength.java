package project.rpg.player.status.thing;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import project.rpg.player.status.base.StatusBase;
import project.rpg.player.status.base.StatusName;

public class Strength extends StatusBase {   //힘

    public Strength() {
        super(StatusName.STRENGTH.getName());
    }

    @Override
    public void effect(Player player) {
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(2d+(this._value-10d)/2d);
    }

}
