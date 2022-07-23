package project.rpg.player.status.thing;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import project.rpg.player.PlayerInformation;
import project.rpg.player.status.Status;
import project.rpg.player.status.base.StatusBase;
import project.rpg.player.status.base.StatusName;

import java.util.Map;
import java.util.Objects;

public class Health extends StatusBase {   //체력

    public Health() {
        super(StatusName.HEALTH);
    }

    @Override
    public boolean addValue(int amount, Status status, Player player){  //스텟 더하기
        if(status.getAdditionalStatusPoint() >= amount) {
            this._value += amount;
            status.minAdditionalStatusPoint(amount);
            PlayerInformation.updateHealth(player);
            return true;
        }
        return false;
    }

    @Override
    public void effect(Player player) {
        Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(20d + (this._value-10d));
    }

    public static Health deserialize(Map<String, String> map){
        Health health = new Health();
        health.setValue(Integer.parseInt(map.get("value")));
        return health;
    }

}
