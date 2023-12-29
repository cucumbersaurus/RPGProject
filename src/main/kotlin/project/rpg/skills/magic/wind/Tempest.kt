package project.rpg.skills.magic.wind

import org.bukkit.Particle
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.util.Vector
import project.rpg.annotation.skill
import project.rpg.player.mana
import project.rpg.skills.SkillType
import project.rpg.skills.magic.MagicSkillBase

class Tempest : MagicSkillBase() {

    init {
        name = SkillType.TEMPEST.skillName
        description = "바라보는 방향으로 돌진하며 주변 적에게 데미지, 출혈, 슬로우를 준다."
        circle = 5
        needMana = 30
    }

    @skill(name = "tempest")
    override fun onEnable(player: Player, action: Action?) {
        val mana = player.mana

        if (mana.useMana(needMana)) {
            player.world.spawnParticle(Particle.WHITE_ASH, player.location, 100, 0.25, 0.5, 0.25, 0.1)
            val vector = Vector(player.location.direction.x, player.location.direction.y, player.location.direction.z)
            player.velocity = vector.multiply(5)
            //TODO : 주변 엔티티 데미지, 슬로우

            player.world.spawnParticle(Particle.WHITE_ASH, player.location, 100, 0.25, 0.5, 0.25, 0.1)
            player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 20 * 5, 2, true))
        }
    }
}