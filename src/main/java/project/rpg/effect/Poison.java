package project.rpg.effect;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Poison extends EffectBase {

    @Override
    public void effect() {
        this._entity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20*_second, 2,true));
    }

    public Poison(LivingEntity entity, int second) {
        super(entity, second);
    }

}
