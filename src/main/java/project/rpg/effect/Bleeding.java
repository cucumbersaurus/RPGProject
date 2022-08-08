package project.rpg.effect;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.util.Objects;

public class Bleeding extends EffectBase {

    @Override
    public void effect() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        try {
            Class.forName("project.rpg.Rpg");
        } catch (ClassNotFoundException exception) {
            return;
        }
        Plugin plugin = pluginManager.getPlugin("Rpg");

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Objects.requireNonNull(plugin), () -> {
            new Damage(_entity, (int) (_entity.getHealth()/100));
            if (!_entity.isDead()) {
                _entity.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, _entity.getLocation(), 7, 0.25, 0.25, 0.25, 0.1);
            }
        },30, 20L * _second);
        //TODO : 지속시간
    }

    public Bleeding(LivingEntity entity, int second) {
        super(entity, second);
    }

}
