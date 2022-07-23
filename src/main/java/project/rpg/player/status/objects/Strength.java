package project.rpg.player.status.objects;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import project.rpg.player.status.base.StatusBase;
import project.rpg.player.status.base.StatusName;

import java.util.Map;

public class Strength extends StatusBase {   //힘

    public Strength() {
        super(StatusName.STRENGTH);
    }

    @Override
    public void effect(Player player) {
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(2d+(this._value-10d)/2d);
    }

    public static Strength deserialize(Map<String, String> map){
        Strength strength = new Strength();
        strength.setValue(Integer.parseInt(map.get("value")));
        return strength;
    }

}
