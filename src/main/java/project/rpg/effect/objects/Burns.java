package project.rpg.effect.objects;

import org.bukkit.entity.LivingEntity;
import project.rpg.effect.EffectBase;

public class Burns extends EffectBase {

    @Override
    public void effect(long durationMillis) {
        this._entity.setFireTicks(this._second*20);
    }

    public Burns(LivingEntity entity, int second) {
        super(entity, second);
    }

}
