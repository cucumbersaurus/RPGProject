package project.rpg.effect.objects;

import org.bukkit.entity.LivingEntity;
import project.rpg.effect.EffectBase;

public class Heal extends EffectBase {

    @Override
    public void effect() {
        if (_entity.getHealth() >= _entity.getMaxHealth()-_amplifier) { //저거 Attribute 로도 된다는데 귀찮아서
            _entity.setHealth(_entity.getMaxHealth());
        } else {
            _entity.setHealth(_entity.getHealth()+_amplifier);
        }
    }

    public Heal(LivingEntity entity, int amplifier) {
        super(entity,1,amplifier);
    }

}
