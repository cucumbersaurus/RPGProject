package project.rpg.effects.objects

import org.bukkit.entity.LivingEntity
import project.rpg.effects.EffectBase

class Sacred(entity: LivingEntity, second: Int) : EffectBase(entity, second) {
    override fun effect(durationMillis: Long) {
        //TODO : 신성 구현 귀찮아
        val damage = entity.lastDamage
    }
}
