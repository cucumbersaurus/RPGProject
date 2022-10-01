package project.rpg.skill.magic.fire

import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import project.rpg.annotation.skill
import project.rpg.effect.Burns
import project.rpg.effect.Damage
import project.rpg.effect.Slow
import project.rpg.extensions.mana
import project.rpg.skill.SkillType
import project.rpg.skill.magic.MagicSkillBase

class Inferno : MagicSkillBase() {

    init {
        name = SkillType.INFERNO.skillName
        description = "단일 적에게 화염 빔을 2초간 발사한다. 이때 체력 재생이 있는 몹은 재생불가로 변하며 슬로우를 부여한다."
        circle = 4
        needMana = 10
    }

    @skill(name = "inferno")
    override fun onEnable(player: Player, action: Action?) {
        val mana = player.mana

        if (mana.useMana(needMana)) {

            val target = player.getTargetBlock(30)
            val distance = target?.let { player.location.subtract(it.location) }
            player.world.spawnParticle(Particle.FLAME,player.location,1000,distance!!.x,distance.y,distance.z,0.01)

            val entity = player.getTargetEntity(30, false)
            if (entity != null && entity is LivingEntity) {
                Slow(entity, 4)
                Burns(entity,10)
                Damage(entity,4)
                //TODO : 재생 불가, 파티클 조정
            }

            player.world.spawnParticle(Particle.FLAME, player.location, 100, 0.25, 3.0, 0.25, 0.1)
            for (p in player.location.getNearbyPlayers(7.0, 7.0, 7.0)) {
                p.playSound(player.location, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 0.6f, 1f)
            }
        }
    }
}