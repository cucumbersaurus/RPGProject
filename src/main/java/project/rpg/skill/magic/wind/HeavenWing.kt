package project.rpg.skill.magic.wind

import org.bukkit.Particle
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import project.rpg.annotation.skill
import project.rpg.extensions.mana
import project.rpg.skill.SkillType
import project.rpg.skill.magic.MagicSkillBase

class HeavenWing : MagicSkillBase() {
    @skill(name = "heaven_wing")
    override fun onEnable(player: Player, action: Action?) {
        val mana = player.mana

        if (mana.useMana(needMana)) {
            with(player) {
                world.spawnParticle(Particle.WHITE_ASH, player.location, 100, 0.25, 0.5, 0.25, 0.1)
                addPotionEffect(PotionEffect(PotionEffectType.LEVITATION, 20 * 2, 3, true))
                addPotionEffect(PotionEffect(PotionEffectType.SLOW_FALLING, 20 * 4, 3, true))
                addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, 20 * 2, 3, true))
            }
        }
    }

    init {
        _name = SkillType.HEAVEN_WING.skillName
        _description = "잠시동안 떠있는다(공중부양 2초와 느린낙하 5초), 떠있는동안 체력을 재생한다."
        circle = 2
        needMana = 10
    }
}