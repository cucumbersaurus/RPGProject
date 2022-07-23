package project.rpg.player.status.thing;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import project.rpg.player.status.base.StatusBase;
import project.rpg.player.status.base.StatusName;

import java.util.Map;

public class Defense extends StatusBase {   //내구

    public Defense() {
        super(StatusName.DEFENSE);
    }

    @Override
    public void effect(Player player) {
        player.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue((this._value-10d)/100d);
    }

    public static Defense deserialize(Map<String, String> map) {
        Defense defense = new Defense();
        defense.setValue(Integer.parseInt(map.get("value")));
        return defense;
    }

}
