package project.rpg.effect.objects

import org.bukkit.entity.LivingEntity
import project.rpg.effect.EffectBase

class Burns(entity: LivingEntity, second: Int) : EffectBase(entity, second) {
    override fun effect(durationMillis: Long) {
        entity.fireTicks = second * 20
    }
}
