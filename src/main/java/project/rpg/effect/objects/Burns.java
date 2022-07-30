package project.rpg.effect.objects;

import org.bukkit.entity.LivingEntity;
import project.rpg.effect.EffectBase;

public class Burns extends EffectBase {

    @Override
    public void effect() {
        this._entity.setFireTicks(this._second);
    }

    public Burns(LivingEntity entity, int second) {
        super(entity, second);
    }

}
