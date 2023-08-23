package project.rpg.skill.magic.electricity

import org.bukkit.Particle
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import project.rpg.annotation.skill
import project.rpg.effect.objects.Damage
import project.rpg.effect.objects.ElectricShock
import project.rpg.extensions.mana
import project.rpg.skill.SkillType
import project.rpg.skill.magic.MagicSkillBase

class ShockWave : MagicSkillBase() {

    init {
        name = SkillType.SHOCK_WAVE.skillName
        description = "주변 반경 3블럭에 적들에게 감전효과 5초를 부여한다. 그리고 본인에게 이동속도 증가를 2초간 부여한다."
        circle = 3
        needMana = 10
    }

    @skill(name = "shock_wave")
    override fun onEnable(player: Player, action: Action?) {
        val mana = player.mana

        if (mana.useMana(needMana)) {
            for (entity in player.getNearbyEntities(3.0, 3.0, 3.0)) {
                if (entity is LivingEntity) {
                    ElectricShock(entity, 5)
                    Damage(entity, 3)
                    entity.world.spawnParticle(Particle.ELECTRIC_SPARK, entity.location, 100, 0.25, 0.5, 0.25, 0.1)
                }
            }
            player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 20 * 2, 2, true))
        }
    }
}