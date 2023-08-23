package project.rpg.events.listeners

import org.bukkit.entity.Arrow
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.event.entity.ProjectileLaunchEvent
import project.rpg.Rpg
import project.rpg.effect.objects.Slow
import project.rpg.effect.objects.Stun
import project.rpg.extensions.mana
import project.rpg.skill.SkillType

class ProjectileEventListener(private val _plugin: Rpg) : Listener {
    @EventHandler
    fun onProjectileHit(event: ProjectileHitEvent) {
        if (event.entity is Arrow) {
            if (event.entity.hasMetadata(SkillType.WATER_ARROW.skillName)) {
                if (event.hitEntity != null && event.hitEntity is LivingEntity) {
                    Stun(event.hitEntity as LivingEntity, 1)
                }
            } else if (event.entity.hasMetadata(SkillType.ICE_SPEAR.skillName)) {
                if (event.hitEntity != null && event.hitEntity is LivingEntity) {
                    Stun(event.hitEntity as LivingEntity, 3)
                    Slow(event.hitEntity as LivingEntity, 5)
                } else if (event.hitBlock != null) {
                    event.entity.world.createExplosion(event.entity.location, 2f)
                    for (target in event.entity.getNearbyEntities(2.0, 2.0, 2.0)) {
                        if (target is LivingEntity) {
                            Slow(target, 2)
                        }
                    }
                }
            } else if (event.entity.hasMetadata(SkillType.THUNDER_CHARGING.skillName)) {
                if (event.hitEntity != null && event.hitEntity is LivingEntity) {
                    Stun(event.hitEntity as LivingEntity, 1)
                    val mana = (event.entity.shooter as Player).mana
                    mana.addMana(1)
                }
            }
        }
    }

    @EventHandler
    fun onProjectileFly(event: ProjectileLaunchEvent) {
        //arrow.world.spawnParticle(Particle.SNOWFLAKE, arrow.location, 100, 0.0, 0.0, 0.0, 0.1)
    }
}
