package project.rpg.skill.magic.fire

import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import project.rpg.effect.Burns
import project.rpg.player.User
import project.rpg.skill.magic.MagicSkillBase

class BlazingMark: MagicSkillBase() {

    override fun onEnable(player: Player, action: Action?) {
        val mana = User.getPlayer(player).mana

        if (mana.useMana(10)) {
            val entity = player.getTargetEntity(10, false)
            if (entity != null && entity is LivingEntity) {
                Burns(entity, 8)
            }
            if (entity is Player) {
                entity.addPotionEffect(PotionEffect(PotionEffectType.WEAKNESS, 20 * 7, 2, true))
            }

            for (p in player.location.getNearbyPlayers(7.0, 7.0, 7.0)) {
                p.playSound(player.location, Sound.BLOCK_BLASTFURNACE_FIRE_CRACKLE, 0.6f, 1f)
            }
        }
        player.world.spawnParticle(Particle.FLAME, player.location, 100, 0.25, 3.0, 0.25, 0.1)
    }
}