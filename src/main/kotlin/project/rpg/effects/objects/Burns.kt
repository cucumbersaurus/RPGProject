package project.rpg.effects.objects

import org.bukkit.entity.LivingEntity
import project.rpg.effects.EffectBase

class Burns(entity: LivingEntity, second: Int) : EffectBase(entity, second) {
    override fun effect(durationMillis: Long) {
        entity.fireTicks = second * 20
    }
}
