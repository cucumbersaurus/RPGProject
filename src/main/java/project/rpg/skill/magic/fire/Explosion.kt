package project.rpg.skill.magic.fire

import org.bukkit.Particle
import org.bukkit.entity.Fireball
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import project.rpg.annotation.skill
import project.rpg.effect.Burns
import project.rpg.effect.Stun
import project.rpg.extensions.mana
import project.rpg.skill.SkillType
import project.rpg.skill.magic.MagicSkillBase

class Explosion : MagicSkillBase() {
    @skill(name = "explosion")
    override fun onEnable(player: Player, action: Action?) {
        val mana = player.mana

        if (mana.useMana(needMana)) {
            val fireball = player.launchProjectile(Fireball::class.java)
            fireball.velocity = player.location.direction.multiply(5)
            fireball.yield = 10f
            fireball.shooter= player
            player.world.spawnParticle(Particle.FLAME, player.location, 100, 0.25, 3.0, 0.25, 0.1)
            while (true) {
                if (fireball.isOnGround) {
                    for ( entity in fireball.getNearbyEntities(3.0, 3.0, 3.0)) {
                        if (entity is LivingEntity) {
                            Stun(entity, 1)
                            Burns(entity,5)
                        }
                    }
                }
            }
        }
    }

    init {
        _name = SkillType.EXPLOSION.skillName
        _description = "화염구를 발사한다. 적중 여부와 상관 없이 터지며 폭발의 여파에 영향을 받은 몹들은 화상효과와 스턴 1초가 부여된다."
        circle = 3
        needMana = 15
    }
}