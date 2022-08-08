package project.rpg.skill.magic.wind

import org.bukkit.Particle
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import project.rpg.annotation.skill
import project.rpg.effect.Bleeding
import project.rpg.player.User
import project.rpg.skill.SkillType
import project.rpg.skill.magic.MagicSkillBase

class WindCutter : MagicSkillBase() {
    @skill(name = "wind_cutter")
    override fun onEnable(player: Player, action: Action?) {
        val mana = User.getPlayer(player).mana

        if (mana.useMana(needMana)) {
            val entity = player.getTargetEntity(10, false)
            if (entity != null && entity is LivingEntity) {
                Bleeding(entity, 3)
                //TODO : 방어력 감소 50% 5초
                entity.world.spawnParticle(Particle.WHITE_ASH, entity.location, 100, 0.25, 0.5, 0.25, 0.1)
            }
        }
    }

    init {
        _name = SkillType.SHOCK_WAVE.skillName
        _description = "주변 반경 3블럭에 적들에게 감전효과 5초를 부여한다. 그리고 본인에게 이동속도 증가를 2초간 부여한다."
        circle = 3
        needMana = 10
    }
}