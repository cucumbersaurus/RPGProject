package project.rpg.effects.objects

import org.bukkit.entity.LivingEntity
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import project.rpg.effects.EffectBase

class Poison(entity: LivingEntity, second: Int) : EffectBase(entity, second) {
    override fun effect(durationMillis: Long) {
        entity.addPotionEffect(PotionEffect(PotionEffectType.POISON, 20 * second, 2, true))
    }
}
