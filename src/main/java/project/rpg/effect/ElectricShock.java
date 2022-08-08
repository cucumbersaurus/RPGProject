package project.rpg.effect;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.util.Objects;

public class ElectricShock extends EffectBase {

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
            new Stun(_entity,1L);
            if (!this._entity.isDead()) {
                this._entity.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, this._entity.getLocation(), 100, 0.25, 0.5, 0.25, 0.1);
            }
        },20, 1);
        //TODO : 지속시간
    }

    public ElectricShock(LivingEntity entity, int second) {
        super(entity, second);
    }


}
