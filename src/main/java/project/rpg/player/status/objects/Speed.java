package project.rpg.player.status.objects;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import project.rpg.player.status.base.StatusBase;
import project.rpg.player.status.base.StatusName;

import java.util.Map;

public class Speed extends StatusBase {   //발 빠르기

    public Speed() {
        super(StatusName.SPEED);
    }

    @Override
    public void effect(Player player) {
        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1d+(this._value-10d)/1000d);
    }

    public static Speed deserialize(Map<String, String> map) {
        Speed speed = new Speed();
        speed.setValue(Integer.parseInt(map.get("value")));
        return speed;
    }

}
