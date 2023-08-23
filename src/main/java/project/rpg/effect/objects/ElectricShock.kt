package project.rpg.effect.objects

import org.bukkit.Bukkit
import org.bukkit.Particle.ELECTRIC_SPARK
import org.bukkit.entity.LivingEntity
import project.rpg.effect.EffectBase
import java.util.*

class ElectricShock(entity: LivingEntity, second: Int) : EffectBase(entity, second) {
    override fun effect(durationMillis: Long) {
        val pluginManager = Bukkit.getPluginManager()
        try {
            Class.forName("project.rpg.Rpg")
        } catch (exception: ClassNotFoundException) {
            return
        }
        val plugin = pluginManager.getPlugin("Rpg")
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Objects.requireNonNull(plugin!!), {
            Stun(entity, 1L)
            if (!entity.isDead) {
                entity.world.spawnParticle(ELECTRIC_SPARK, entity.location, 7, 0.25, 0.5, 0.25, 0.1)
            }
        }, 20, 1)
        //TODO : 지속시간
    }
}
