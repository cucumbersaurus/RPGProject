package project.rpg.effect.objects;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import project.rpg.Rpg;
import project.rpg.effect.EffectBase;

public class ElectricShock extends EffectBase {

    @Override
    public void effect() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this._plugin, () -> {
            this._entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 2, 7,true));
        },10, 20L *_second);
    }

    public ElectricShock(LivingEntity entity, int second, Rpg plugin) {
        super(entity, second, plugin);
    }


}
