package project.rpg.player.status.thing;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import project.rpg.player.status.base.StatusBase;
import project.rpg.player.status.base.StatusName;

public class Health extends StatusBase {   //체력

    public Health() {
        super(StatusName.HEALTH.getName());
    }

    @Override
    public void effect(Player player) {
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20d + (this._value-10d));
    }

}
