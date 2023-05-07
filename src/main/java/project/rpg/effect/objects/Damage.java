package project.rpg.effect.objects;

import org.bukkit.entity.LivingEntity;
import project.rpg.effect.EffectBase;

public class Damage extends EffectBase {

    @Override
    public void effect(long durationMillis) {
        if (_entity.getHealth() > _amplifier) {
            _entity.damage(10.0);
        } else {
            _entity.damage(_entity.getHealth());
        }
    }

    public Damage(LivingEntity entity, int amplifier) {
        super(entity, 1, amplifier);
    }
}
