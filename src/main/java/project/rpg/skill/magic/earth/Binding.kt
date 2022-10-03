package project.rpg.skill.magic.earth

import org.bukkit.Particle
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import project.rpg.annotation.skill
import project.rpg.effect.objects.Damage
import project.rpg.effect.objects.Stun
import project.rpg.extensions.mana
import project.rpg.skill.SkillType
import project.rpg.skill.magic.MagicSkillBase
class Binding : MagicSkillBase() {
    @skill(name = "tempest")
    override fun onEnable(player: Player, action: Action?) {
        val mana = player.mana

        if (mana.useMana(needMana)) {
            val entity = player.getTargetEntity(20, false)
            if (entity!=null && entity is LivingEntity) {
                Damage(entity, 1)
                Stun(entity, 4)
                entity.world.spawnParticle(Particle.WHITE_ASH, entity.location, 100, 0.25, 0.5, 0.25, 0.1)
            }
            player.world.spawnParticle(Particle.WHITE_ASH, player.location, 100, 0.25, 0.5, 0.25, 0.1)
        }
    }

    init {
        _name = SkillType.BINDING.skillName
        _description = "적 하나를 조준하여 투사체를 발사합니다. 이 투사체에 적중 시 적에게 스턴을 4초간 부여합니다."
        circle = 2
        needMana = 10
    }
}

