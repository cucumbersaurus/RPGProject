package project.rpg.skill.magic.electricity

import org.bukkit.Particle
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import project.rpg.annotation.skill
import project.rpg.effect.Damage
import project.rpg.effect.ElectricShock
import project.rpg.player.User
import project.rpg.skill.SkillType
import project.rpg.skill.magic.MagicSkillBase

class LightningChain : MagicSkillBase() {
    @skill(name = "lightning_chain")
    override fun onEnable(player: Player, action: Action?) {
        val mana = User.getPlayer(player).mana

        if (mana.useMana(needMana)) {
            val entity = player.getTargetEntity(10, false)
            if (entity!=null && entity is LivingEntity) {
                Damage(entity,4)
                ElectricShock(entity,2)
                entity.world.spawnParticle(Particle.ELECTRIC_SPARK, entity.location, 100, 0.25, 0.5, 0.25, 0.1)
                var count = 1
                for (target in entity.getNearbyEntities(4.0,4.0,4.0)) {
                    if (target is LivingEntity) {
                        Damage(entity,4)
                        ElectricShock(entity,2)
                        target.world.spawnParticle(Particle.ELECTRIC_SPARK, target.location, 100, 0.25, 0.5, 0.25, 0.1)
                        count++
                    }
                    if (count == 4) {
                        break;
                    }
                }
            }
        }
    }

    init {
        _name = SkillType.LIGHTNING_CHAIN.skillName
        _description = "몹에게 번개를 날리는데 적중시 주변 다른 몹에게도 동일한 양의 데미지와 감전 2초를 부여한다. (최대 4회까지)"
        circle = 2
        needMana = 7
    }
}