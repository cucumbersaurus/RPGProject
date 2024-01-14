package project.rpg.mob

import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
import net.minecraft.world.entity.ai.goal.RangedAttackGoal
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.animal.SnowGolem
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level

class TestMob(type: EntityType<out SnowGolem>, world: Level) : SnowGolem(type, world) {

    init{
        this.clearEquipmentSlots = true
    }

    override fun registerGoals() {
        goalSelector.addGoal(1, RangedAttackGoal(this, 1.5, 20, 10.0f))
        goalSelector.addGoal(2, WaterAvoidingRandomStrollGoal(this, 1.0, 1.0000001E-5f))
        goalSelector.addGoal(4, RandomLookAroundGoal(this))
        targetSelector.addGoal(
            1, NearestAttackableTargetGoal(
                this,
                Player::class.java, 10, true, false
            ) { entityliving: Any? -> true }
        )
    }

}