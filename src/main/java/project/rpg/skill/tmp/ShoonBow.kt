package project.rpg.skill.tmp

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import project.rpg.skill.SkillType
import project.rpg.skill.magic.MagicSkillBase

class ShoonBow(p: Player?) : MagicSkillBase() {
    override fun onEnable(player: Player, action: Action?) {
        if (skillTime == 0) {
            player.world.spawnParticle(Particle.GLOW, player.location, 25, 0.5, 1.0, 0.5, 0.7)
            var location: Location? = null
            for (all in Bukkit.getOnlinePlayers()) {
                all.playSound(player.location, Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 0.6f, 2f)
                if (all != player) {
                    if (location == null || player.location.distance(all.location) < player.location.distance(location)) {
                        location = all.location
                    }
                    location.subtract(location.direction).subtract(location.direction)
                    player.teleport(location)
                    skillTime = coolTime
                }
            }
        }
    }

    init {
        name = SkillType.SHOONBOW.skillName
        description = "사람에게 술식을 새겼다."
        circle = 6
        needMana = 0
    }
}