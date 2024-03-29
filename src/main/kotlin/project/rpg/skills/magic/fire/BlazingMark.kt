package project.rpg.skills.magic.fire

import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import project.rpg.effects.objects.Burns
import project.rpg.player.mana
import project.rpg.skills.SkillType
import project.rpg.skills.magic.MagicSkillBase

class BlazingMark : MagicSkillBase() {

    init {
        name = SkillType.BLAZING_MARK.skillName
        description = "단일 몹에게 화상 8초 부여와 방어력 20% 감소 7초 효과를 건다. (중첩 가능)"
        circle = 2
        needMana = 5
    }

    override fun onEnable(player: Player, action: Action?) {
        val mana = player.mana

        if (mana.useMana(needMana)) {
            val entity = player.getTargetEntity(10, false)
            if (entity != null && entity is LivingEntity) {
                Burns(entity, 8)
                //TODO : 방어력 감소 20% 7초
                player.world.spawnParticle(Particle.FLAME, player.location, 100, 0.25, 3.0, 0.25, 0.1)
            }
            if (entity is Player) {
                entity.addPotionEffect(PotionEffect(PotionEffectType.WEAKNESS, 20 * 7, 2, true))
            }

            for (p in player.location.getNearbyPlayers(7.0, 7.0, 7.0)) {
                p.playSound(player.location, Sound.BLOCK_BLASTFURNACE_FIRE_CRACKLE, 0.6f, 1f)
            }
        }
    }
}