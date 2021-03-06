package project.rpg.effect;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Slow extends EffectBase {

    @Override
    public void effect() {
        this._entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20*_second, 2,true));
    }

    public Slow(LivingEntity entity, int second) {
        super(entity, second);
    }

}
