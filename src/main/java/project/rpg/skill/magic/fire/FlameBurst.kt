package project.rpg.skill.magic.fire

import org.bukkit.Particle
import org.bukkit.entity.Fireball
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import project.rpg.annotation.skill
import project.rpg.player.User
import project.rpg.skill.SkillType
import project.rpg.skill.magic.MagicSkillBase

class FlameBurst : MagicSkillBase() {
    @skill(name = "flame_burst")
    override fun onEnable(player: Player, action: Action?) {
        val mana = User.getPlayer(player).mana

        if (mana.useMana(10)) {
            val fireball: Fireball = player.launchProjectile(Fireball::class.java)
            fireball.velocity = player.location.direction.multiply(5)
        }
        player.world.spawnParticle(Particle.FLAME, player.location, 100, 0.25, 3.0, 0.25, 0.1)
    }

    init {
        _name = SkillType.FLAME_BURST.skillName
        _description = "몹에게 화염구를 발사한다. 적중 시 몹에게 화상 효과를 부여하며 방어력을 2초간 무시한다."
        circle = 3
        needMana = 5
    }
}