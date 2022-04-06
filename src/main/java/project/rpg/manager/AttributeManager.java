package project.rpg.manager;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import project.rpg.player.info.Status;

public class AttributeManager {

    public static void setAttributes(Player player, Status status) {

        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20d + (status.getHealth()-10d));
        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1d+(status.getSpeed()-10d)/1000d);
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue( 2d+(status.getStrength()-10d)/2d);
        player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue( 4d+(status.getAgility()-10d)/10d);
        player.getAttribute(Attribute.GENERIC_LUCK).setBaseValue((status.getLuck()-10d)*10d);
        player.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue((double) (status.getDefense()-10d)/100d);

    }

}
