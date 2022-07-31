package project.rpg.skill.magic.fire

import org.bukkit.Particle
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import project.rpg.annotation.skill
import project.rpg.effect.Burns
import project.rpg.effect.Slow
import project.rpg.items.weapon.LavaZone
import project.rpg.player.User
import project.rpg.skill.SkillType
import project.rpg.skill.magic.MagicSkillBase

class LavaZone : MagicSkillBase() {
    @skill(name = "lava_zone")
    override fun onEnable(player: Player, action: Action?) {
        val mana = User.getPlayer(player).mana

        if (mana.useMana(10)) {
            player.world.spawnParticle(Particle.FLAME, player.location, 1000, 5.0, 0.0, 5.0, 0.1)
            for (entity in player.getNearbyEntities(5.0,0.0,5.0)) {
                if (entity is LivingEntity) {
                    Slow(entity, 5)
                    Burns(entity,5)
                }
            }
        }
    }

    init {
        _name = SkillType.LAVA_ZONE.skillName
        _description = "몹에게 화염구를 발사한다. 적중 시 몹에게 화상 효과를 부여하며 방어력을 2초간 무시한다."
        circle = 3
        needMana = 5
    }
}