package project.rpg.skill.magic.fire

import org.bukkit.GameMode
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import project.rpg.annotation.skill
import project.rpg.effect.objects.Burns
import project.rpg.effect.objects.Damage
import project.rpg.effect.objects.Slow
import project.rpg.effect.objects.Stun
import project.rpg.extensions.mana
import project.rpg.skill.SkillType
import project.rpg.skill.magic.MagicSkillBase

class MeteorStrike : MagicSkillBase() {

    init {
        name = SkillType.METEOR_STRIKE.skillName
        description = "반경 7블럭에 적들에게 메테오를 난사한다. 범위 내의 적들에게 스턴 1초와 방어력 50% 감소를 2초간 부여하며 화상효과도 부여한다."
        circle = 4
        needMana = 10
    }

    @skill(name = "meteor")
    override fun onEnable(player: Player, action: Action?) {
        val mana = player.mana
        if (mana.useMana(this.needMana)) {

            for (entity in player.getNearbyEntities(7.0, 7.0, 7.0)) {
                if (entity is Player && entity.gameMode == GameMode.CREATIVE) {
                    continue
                }

                if (entity is LivingEntity) {
                    entity.world.spawnParticle(Particle.FLAME, entity.location, 400, 0.25, 3.0, 0.25, 0.1)
                    Burns(entity, 2)
                    Stun(entity, 1)
                    //TODO : 방어력 50% 감소

                    if (player != entity) {
                        Damage(entity, 10)

                        entity.fireTicks = 20 * 2
                        Slow(entity, 20)
                    }
                }
            }
            for (p in player.location.getNearbyPlayers(7.0, 7.0, 7.0)) {
                p.playSound(player.location, Sound.ENTITY_GENERIC_EXPLODE, 0.6f, 1f)
            }
            player.world.spawnParticle(Particle.FLAME, player.location, 100, 0.25, 3.0, 0.25, 0.1)
        }
    }
}