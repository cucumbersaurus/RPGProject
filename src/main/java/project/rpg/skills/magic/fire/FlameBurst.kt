package project.rpg.skills.magic.fire

import org.bukkit.Particle
import org.bukkit.entity.Fireball
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import project.rpg.annotation.skill
import project.rpg.extensions.mana
import project.rpg.skills.SkillType
import project.rpg.skills.magic.MagicSkillBase

class FlameBurst : MagicSkillBase() {

    init {
        name = SkillType.FLAME_BURST.skillName
        description = "몹에게 화염구를 발사한다. 적중 시 몹에게 화상 효과를 부여하며 방어력을 2초간 무시한다."
        circle = 3
        needMana = 10
    }

    @skill(name = "flame_burst")
    override fun onEnable(player: Player, action: Action?) {
        val mana = player.mana

        if (mana.useMana(needMana)) {
            val fireball: Fireball = player.launchProjectile(Fireball::class.java)
            fireball.velocity = player.location.direction.multiply(2)
            //TODO : 방어력 2초간 무시
            player.world.spawnParticle(Particle.FLAME, player.location, 100, 0.25, 3.0, 0.25, 0.1)
        }
    }
}