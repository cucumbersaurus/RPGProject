package project.rpg.mob

import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.animal.SnowGolem
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.projectile.Snowball
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld
import org.bukkit.entity.Entity
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.persistence.PersistentDataType
import project.rpg.Rpg
import kotlin.math.sqrt

class SnowMonster(loc: Location) :SnowGolem(EntityType.SNOW_GOLEM, (loc.world as CraftWorld).handle)  {

    init {
        setPosRaw(loc.x, loc.y, loc.z)

        goalSelector.addGoal(1, MeleeAttackGoal(this, 1.0, true))
        targetSelector.addGoal(1, NearestAttackableTargetGoal(this, Player::class.java, true))

        setPumpkin(false)
        //persist = true

        this.bukkitEntity.persistentDataContainer[Rpg.pluginNamespacedKey("snow_monster"), PersistentDataType.BOOLEAN] =
            true

        (loc.world as CraftWorld).handle.addFreshEntity(this, CreatureSpawnEvent.SpawnReason.CUSTOM)
    }

    companion object: MobBase {
        fun performRangeAttack(entity: LivingEntity, monster: SnowGolem) {
            val snowball = Snowball(monster.level(), monster)
            val d0 = entity.eyeY - 1.100000023841858
            val d1 = entity.x - monster.x
            val d2 = d0 - snowball.y
            val d3 = entity.z - monster.z
            val d4 = sqrt(d1 * d1 + d3 * d3) * 0.20000000298023224

            snowball.bukkitEntity.persistentDataContainer[Rpg.pluginNamespacedKey("mega_snow"), PersistentDataType.BOOLEAN] =
                true

            snowball.shoot(d1, d2 + d4, d3, 1.0f, 10.0f)
            monster.playSound(
                SoundEvent.createVariableRangeEvent(SoundEvents.SHULKER_SHOOT.location),
                1.0f,
                0.4f / monster.random.nextFloat() * 0.4f + 0.8f
            )

            monster.level().addFreshEntity(snowball)
        }

        override fun spawn(world: World, loc: Location): Entity {
            val monster = SnowMonster(loc)
            return monster.bukkitEntity
        }
    }


}