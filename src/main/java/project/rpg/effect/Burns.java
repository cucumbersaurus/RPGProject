package project.rpg.effect;

import org.bukkit.entity.LivingEntity;

public class Burns extends EffectBase {

    @Override
    public void effect() {
        this._entity.setFireTicks(this._second*20);
    }

    public Burns(LivingEntity entity, int second) {
        super(entity, second);
    }

}
