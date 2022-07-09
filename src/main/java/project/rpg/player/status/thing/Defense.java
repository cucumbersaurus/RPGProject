package project.rpg.player.status.thing;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import project.rpg.player.status.base.StatusBase;
import project.rpg.player.status.base.StatusName;

public class Defense extends StatusBase {   //내구

    public Defense() {
        super(StatusName.DEFENSE.getName());
    }

    @Override
    public void effect(Player player) {
        player.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue((this._value-10d)/100d);
    }

}
