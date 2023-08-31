package project.rpg.effects.objects

import io.github.monun.heartbeat.coroutines.Heartbeat
import kotlinx.coroutines.*
import org.bukkit.Particle
import org.bukkit.entity.LivingEntity
import project.rpg.effects.EffectBase

class Bleeding(entity: LivingEntity, second: Int) : EffectBase(entity, second) {
    override fun effect(durationMillis: Long) {
        try {
            Class.forName("project.rpg.Rpg")
        } catch (exception: ClassNotFoundException) {
            return
        }
        //val plugin = pluginManager.getPlugin("Rpg")

        CoroutineScope(Dispatchers.Heartbeat).launch {
            println(durationMillis)
            withTimeout(durationMillis) {
                while (true) {
                    Damage(entity, (entity.health / 100).toInt())
                    if (!entity.isDead) {
                        entity.world.spawnParticle(
                            Particle.DAMAGE_INDICATOR,
                            entity.location,
                            7,
                            0.25,
                            0.25,
                            0.25,
                            0.1
                        )
                    }
                    delay(1000L)
                }
            }
        }
//
//        HeartbeatScope().launch {
//            val suspension = Suspension()
//            suspension.delay(1000L)
//            withTimeout(durationMillis){
//                Damage(_entity, (_entity.health / 100).toInt())
//                if (!_entity.isDead) {
//                    _entity.world.spawnParticle(Particle.DAMAGE_INDICATOR, _entity.location, 7, 0.25, 0.25, 0.25, 0.1)
//                }
//                yield()
//            }
//        }
    }
}
