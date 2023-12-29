package project.rpg.effects.objects

import org.bukkit.entity.LivingEntity
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import project.rpg.effects.EffectBase

class Stun : EffectBase {
    override fun effect(durationMillis: Long) {
        entity.addPotionEffect(PotionEffect(PotionEffectType.SLOW, 20 * second, 7, false, false, false))
    }

    constructor(entity: LivingEntity, second: Int) : super(entity, second)
    constructor(entity: LivingEntity, tick: Long) : super(entity, tick)
}
