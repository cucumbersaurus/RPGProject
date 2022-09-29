package project.rpg.player.status.objects;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import project.rpg.player.status.base.StatusBase;
import project.rpg.player.status.base.StatusName;

import java.util.Map;

public class Agility extends StatusBase {   //공격 속도

    public Agility() {
        super(StatusName.AGILITY);
    }

    @Override
    public void effect(Player player) {
        player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(4d+(this._value-10d)/10d);
    }

    public static Agility deserialize(Map<String, String> map){
        Agility agility = new Agility();
        agility.setValue(Integer.parseInt(map.get("value")));
        return agility;
    }

}
