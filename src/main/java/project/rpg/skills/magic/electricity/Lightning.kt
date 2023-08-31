package project.rpg.skills.magic.electricity

import org.bukkit.Particle
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import project.rpg.annotation.skill
import project.rpg.extensions.mana
import project.rpg.skills.magic.MagicSkillBase

class Lightning : MagicSkillBase() {

    @skill(name = "lightning")
    override fun onEnable(player: Player, action: Action?) {
        var location = player.location
        val mana = player.mana
        if (mana.useMana(10)) {
            if (player.getTargetBlock(30) != null) {
                location = player.getTargetBlock(30)!!.location
            }

            player.world.spawnEntity(location, EntityType.LIGHTNING)
            player.world.spawnParticle(Particle.ELECTRIC_SPARK, player.location, 100, 0.25, 0.75, 0.25, 0.1)
        }
    }

}