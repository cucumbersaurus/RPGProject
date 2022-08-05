package project.rpg.skill.magic.electricity

import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import project.rpg.annotation.skill
import project.rpg.player.User
import project.rpg.skill.magic.MagicSkillBase

class Lightning: MagicSkillBase() {

    @skill(name="lightning")
    override fun onEnable(player: Player, action: Action?) {
        var location = player.location
        val mana = User.getPlayer(player).mana
        if (mana.useMana(10)) {
            if (player.getTargetBlock(30) != null) {
                location = player.getTargetBlock(30)!!.location
            }

            player.world.spawnEntity(location, EntityType.LIGHTNING)
        }
    }

}