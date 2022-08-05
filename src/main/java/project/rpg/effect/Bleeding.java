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
            _entity.setHealth(_entity.getHealth()*0.99);
            _entity.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, _entity.getLocation(), 100, 0.25, 0.25, 0.25, 0.1);
        },20, 20L *_second);
    }

    public Bleeding(LivingEntity entity, int second) {
        super(entity, second);
    }

}
