package project.rpg.skill.magic.fire

import org.bukkit.GameMode
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import project.rpg.annotation.skill
import project.rpg.player.User
import project.rpg.skill.SkillType
import project.rpg.skill.magic.MagicSkillBase

class MeteorStrike : MagicSkillBase() {

    @skill(name = "meteor")
    override fun onEnable(player: Player, action: Action?) {
        val mana = User.getPlayer(player).mana
        if (mana.useMana(this.needMana)) {

            for (entity in player.getNearbyEntities(7.0, 7.0, 7.0)) {
                if (entity is Player && entity.gameMode == GameMode.CREATIVE) {
                    continue
                }

                if (entity is LivingEntity) {
                    entity.world.spawnParticle(Particle.FLAME, entity.location, 400, 0.25, 3.0, 0.25, 0.1)

                    if (player != entity) {
                        if (entity.health > 10) {
                            entity.damage(10.0)

                        } else {
                            entity.damage(entity.health)
                        }

                        entity.fireTicks = 20 * 2
                        entity.addPotionEffect(PotionEffect(PotionEffectType.SLOW, 20, 2, true))
                    }
                }
            }
            for (p in player.location.getNearbyPlayers(7.0, 7.0, 7.0)) {
                p.playSound(player.location, Sound.ENTITY_GENERIC_EXPLODE, 0.6f, 1f)
            }
            player.world.spawnParticle(Particle.FLAME, player.location, 100, 0.25, 3.0, 0.25, 0.1)
        }
    }

    init {
        _name = SkillType.METEOR_STRIKE.skillName
        _description = "반경 7블럭에 적들에게 메테오를 난사한다. 범위 내의 적들에게 스턴 1초와 방어력 50% 감소를 2초간 부여하며 화상효과도 부여한다."
        circle = 4
        needMana = 10
    }
}