package project.rpg.effect.objects;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import project.rpg.effect.EffectBase;

public class Sacred extends EffectBase {

    @Override
    public void effect() {
        //TODO : 신성 구현 귀찮아
    }

    public Sacred(LivingEntity entity, int second) {
        super(entity, second);
    }

}
