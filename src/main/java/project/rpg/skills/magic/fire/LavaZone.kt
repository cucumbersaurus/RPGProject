package project.rpg.skills.magic.fire

import org.bukkit.Particle
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import project.rpg.annotation.skill
import project.rpg.effects.objects.Burns
import project.rpg.effects.objects.Slow
import project.rpg.extensions.mana
import project.rpg.skills.SkillType
import project.rpg.skills.magic.MagicSkillBase

class LavaZone : MagicSkillBase() {

    init {
        name = SkillType.LAVA_ZONE.skillName
        description = "몹에게 화염구를 발사한다. 적중 시 몹에게 화상 효과를 부여하며 방어력을 2초간 무시한다."
        circle = 3
        needMana = 10
    }

    @skill(name = "lava_zone")
    override fun onEnable(player: Player, action: Action?) {
        val mana = player.mana

        if (mana.useMana(needMana)) {
            player.world.spawnParticle(Particle.FLAME, player.location, 1000, 5.0, 0.0, 5.0, 0.01)
            for (entity in player.getNearbyEntities(5.0, 0.0, 5.0)) {
                if (entity is LivingEntity) {
                    Slow(entity, 5)
                    Burns(entity, 5)
                }
            }
        }
    }
}