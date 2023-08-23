package project.rpg.effect.objects

import org.bukkit.entity.LivingEntity
import project.rpg.effect.EffectBase

class Damage(entity: LivingEntity, amplifier: Int) : EffectBase(entity, 1, amplifier) {
    override fun effect(durationMillis: Long) {
        if (entity.health > amplifier) {
            entity.damage(10.0)
        } else {
            entity.damage(entity.health)
        }
    }
}
