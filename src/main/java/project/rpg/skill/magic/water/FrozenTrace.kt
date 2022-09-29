package project.rpg.skill.magic.water

import org.bukkit.Particle
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.util.Vector
import project.rpg.annotation.skill
import project.rpg.extensions.mana
import project.rpg.skill.SkillType
import project.rpg.skill.magic.MagicSkillBase

class FrozenTrace: MagicSkillBase() {
    @skill(name = "frozen_trace")
    override fun onEnable(player: Player, action: Action?) {
        val mana = player.mana

        if (mana.useMana(needMana)) {
            val vector = Vector(player.location.direction.x,0.1,player.location.direction.z)
            player.velocity = vector.multiply(2)
            //TODO : 지나친 적들에게 스턴 3초외 슬로우 4초를 부여한다.

            player.world.spawnParticle(Particle.WATER_DROP, player.location, 100, 0.25, 3.0, 0.25, 0.1)
        }
    }

    init {
        _name = SkillType.FROZEN_TRACE.skillName
        _description = "전방으로 6칸 대쉬하며 지나친 적들에게 스턴 3초외 슬로우 4초를 부여한다."
        circle = 2
        needMana = 7
    }
}