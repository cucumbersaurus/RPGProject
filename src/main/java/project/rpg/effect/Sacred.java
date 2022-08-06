package project.rpg.effect;

import org.bukkit.entity.LivingEntity;

public class Sacred extends EffectBase {

    @Override
    public void effect() {
        //TODO : 신성 구현 귀찮아
        double damage = this._entity.getLastDamage();
    }

    public Sacred(LivingEntity entity, int second) {
        super(entity, second);
    }

}
