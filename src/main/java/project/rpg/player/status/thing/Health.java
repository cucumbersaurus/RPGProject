package project.rpg.player.status.thing;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import project.rpg.player.PlayerInformation;
import project.rpg.player.status.Stats;
import project.rpg.player.status.base.StatusBase;
import project.rpg.player.status.base.StatusName;

import java.util.Objects;

public class Health extends StatusBase {   //체력

    public Health() {
        super(StatusName.HEALTH);
    }

    @Override
    public boolean addValue(int amount, Stats status, Player player){  //스텟 더하기
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

}
