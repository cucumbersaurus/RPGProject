package project.rpg.skill.magic.water

import org.bukkit.Particle
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import project.rpg.annotation.skill
import project.rpg.effect.Stun
import project.rpg.player.User
import project.rpg.skill.SkillType
import project.rpg.skill.magic.MagicSkillBase

class EternalFrost : MagicSkillBase() {
    @skill(name = "eternal_frost")
    override fun onEnable(player: Player, action: Action?) {
        val mana = User.getPlayer(player).mana

        if (mana.useMana(needMana)) {
            for (entity in player.getNearbyEntities(7.0,7.0,7.0)) {
                if (entity is LivingEntity) {
                    Stun(entity,3)
                    //Damage(entity,entity.health)
                }
            }

            player.world.spawnParticle(Particle.WATER_DROP, player.location, 100, 0.25, 3.0, 0.25, 0.1)
        }
    }

    init {
        _name = SkillType.ICE_SPEAR.skillName
        _description = "몹에게 적중 시 넉백 1칸 과 스턴 0.2초"
        circle = 2
        needMana = 5
    }
}