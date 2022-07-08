package project.rpg.skill.magic.fire

import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import project.rpg.skill.SkillType
import project.rpg.skill.base.MagicSkillBase

class MeteorStrike(player: Player?) : MagicSkillBase() {
    override fun onEnable(player:Player, action: Action) {
        for (entity in _player.getNearbyEntities(7.0, 7.0, 7.0)) {
            if (entity is Player) {
                continue
            }
            if (entity is LivingEntity) {
                entity.world.spawnParticle(Particle.FLAME, entity.location, 400, 0.25, 3.0, 0.25, 0.1)
                if (_player != entity) {
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
        for (p in _player.location.getNearbyPlayers(7.0, 7.0, 7.0)) {
            p.playSound(_player.location, Sound.ENTITY_GENERIC_EXPLODE, 0.6f, 1f)
        }
        _player.world.spawnParticle(Particle.FLAME, _player.location, 100, 0.25, 3.0, 0.25, 0.1)
    }

    init {
        _player = player
        _name = SkillType.METEOR_STRIKE.skillName
        _description = "반경 7블럭에 적들에게 메테오를 난사한다. 범위 내의 적들에게 스턴 1초와 방어력 50% 감소를 2초간 부여하며 화상효과도 부여한다."
        circle = 4
        needMana = 10
    }
}