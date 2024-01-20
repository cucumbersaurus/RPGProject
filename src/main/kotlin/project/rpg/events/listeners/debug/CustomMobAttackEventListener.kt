package project.rpg.events.listeners.debug

import net.minecraft.world.entity.animal.SnowGolem
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftProjectile
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.event.entity.ProjectileLaunchEvent
import org.bukkit.persistence.PersistentDataType
import project.rpg.Rpg
import project.rpg.mob.SnowMonster

class CustomMobAttackEventListener: Listener {

    @EventHandler
    fun onMobAttackEvent(event: ProjectileLaunchEvent) {
        val projectile = event.entity
        if (projectile is CraftProjectile) {
            val owner = projectile.handle.owner ?: return
            if (owner.bukkitEntity.persistentDataContainer[Rpg.pluginNamespacedKey("snow_monster"), PersistentDataType.BOOLEAN] == true) {
                if (projectile.persistentDataContainer[Rpg.pluginNamespacedKey("mega_snow"), PersistentDataType.BOOLEAN] != true) {
                    SnowMonster.performRangeAttack((owner as SnowGolem).target ?: return, owner)
                    event.isCancelled = true
                }
            }
        }
    }

    @EventHandler
    fun onSnowballHit(event: ProjectileHitEvent){
        val projectile = event.entity
        if(projectile is CraftProjectile) {
            if (projectile.persistentDataContainer[Rpg.pluginNamespacedKey("mega_snow"), PersistentDataType.BOOLEAN] == true) {
                val entity = event.hitEntity ?: return
                if ((entity as CraftEntity).handle !is SnowGolem) {
                    if(entity is LivingEntity) {
                        entity.damage(15.0)
                    }
                }
            }
        }
    }
}