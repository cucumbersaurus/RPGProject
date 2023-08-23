package project.rpg.effect.objects

import org.bukkit.entity.LivingEntity
import project.rpg.effect.EffectBase

class Heal(entity: LivingEntity, amplifier: Int) : EffectBase(entity, 1, amplifier) {
    override fun effect(durationMillis: Long) {
        if (entity.health >= entity.maxHealth - amplifier) { //저거 Attribute 로도 된다는데 귀찮아서
            entity.health = entity.maxHealth
        } else {
            entity.health = entity.health + amplifier
        }
    }
}
