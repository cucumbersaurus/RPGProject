package project.rpg.effect.objects;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import project.rpg.effect.EffectBase;

public class Stun extends EffectBase {

    @Override
    public void effect() {
        this._entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20*_second, 7,true));
    }

    public Stun(LivingEntity entity, int second) {
        super(entity, second);
    }

}
