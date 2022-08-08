package project.rpg.effect;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Stun extends EffectBase {

    @Override
    public void effect() {
        this._entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20*_second, 7,false,false,false));
    }

    public Stun(LivingEntity entity, int second) {
        super(entity, second);
    }
    public Stun(LivingEntity entity,long tick) {
        super(entity,tick);
    }

}
