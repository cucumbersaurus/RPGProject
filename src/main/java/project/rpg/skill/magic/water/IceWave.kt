package project.rpg.skill.magic.water

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

class IceWave : MagicSkillBase() {

    init {
        name = SkillType.ICE_WAVE.skillName
        description = "몹에게 적중 시 넉백 1칸 과 스턴 0.2초"
        circle = 1
        needMana = 5
    }

    @skill(name = "ice_wave")
    override fun onEnable(player: Player, action: Action?) {
        val mana = player.mana

        if (mana.useMana(needMana)) {
            for (entity in player.getNearbyEntities(4.0, 4.0, 4.0)) {
                if (entity is LivingEntity) {
                    Stun(entity, 4)
                    Damage(entity, 4)
                }
            }
            player.world.spawnParticle(Particle.WATER_DROP, player.location, 1000, 4.0, 0.0, 4.0, 0.01)
        }
    }
}