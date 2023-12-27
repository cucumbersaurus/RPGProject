package project.rpg.skills.magic.electricity

import org.bukkit.Particle
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import project.rpg.annotation.skill
import project.rpg.player.mana
import project.rpg.skills.SkillType
import project.rpg.skills.magic.MagicSkillBase

class LightningStorm : MagicSkillBase() {

    init {
        name = SkillType.LIGHTNING_STORM.skillName
        description = "번개구름을 소환한다. 번개에 맞은 적은 감전 3초가 부여되며 공격력이 3초간 10% 증가한다."
        circle = 2
        needMana = 5
    }

    @skill(name = "lightning_storm")
    override fun onEnable(player: Player, action: Action?) {
        val mana = player.mana
        var location = player.location

        if (mana.useMana(needMana)) {
            if (player.getTargetBlock(20) != null) {
                player.world.getNearbyEntities(player.getTargetBlock(20)!!.location, 0.5, 0.5, 0.5)
                location = player.getTargetBlock(20)!!.location
            }
            //TODO: 위치 조정
            player.world.spawnParticle(Particle.ELECTRIC_SPARK, player.location, 100, 0.25, 0.75, 0.25, 0.1)
            player.world.spawnParticle(Particle.WHITE_ASH, location.add(0.0, 10.0, 0.0), 3000, 8.0, 0.5, 8.0, 0.01)
            player.world.spawnEntity(location, EntityType.LIGHTNING)
        }
    }
}

