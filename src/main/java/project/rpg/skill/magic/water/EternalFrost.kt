package project.rpg.skill.magic.water

import org.bukkit.Particle
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import project.rpg.annotation.skill
import project.rpg.effect.Damage
import project.rpg.effect.Stun
import project.rpg.extensions.mana
import project.rpg.skill.SkillType
import project.rpg.skill.magic.MagicSkillBase

class EternalFrost : MagicSkillBase() {
    @skill(name = "eternal_frost")
    override fun onEnable(player: Player, action: Action?) {
        val mana = player.mana

        if (mana.useMana(needMana)) {
            for (entity in player.getNearbyEntities(7.0,7.0,7.0)) {
                if (entity is LivingEntity) {
                    Stun(entity,3)
                    Damage(entity, (entity.maxHealth/5).toInt())
                    entity.world.spawnParticle(Particle.TOWN_AURA, entity.location, 100, 0.25, 0.5, 0.25, 0.01)
                }
            }
            player.world.spawnParticle(Particle.SNOWFLAKE, player.location, 3000, 7.0, 0.0, 7.0, 0.01)
        }
    }

    init {
        _name = SkillType.ETERNAL_FROST.skillName
        _description = "적 최대 체력의 20%를 깎는다. (보스몹은 슬로우 8초와 방어력 30% 감소로 대체)"
        circle = 4
        needMana = 20
    }
}