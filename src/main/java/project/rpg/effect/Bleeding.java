package project.rpg.effect;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import project.rpg.Rpg;

public class Bleeding extends EffectBase {

    @Override
    public void effect() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this._plugin, () -> {
            _entity.setHealth(_entity.getHealth()*0.99);
            _entity.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, _entity.getLocation(), 100, 0.25, 0.25, 0.25, 0.1);
        },20, 20L *_second);
    }

    public Bleeding(LivingEntity entity, int second, Rpg plugin) {
        super(entity, second, plugin);
    }

}
