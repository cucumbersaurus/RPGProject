package project.rpg.skills.magic.electricity

import org.bukkit.Particle
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.event.entity.CreatureSpawnEvent
import project.rpg.annotation.skill
import project.rpg.player.mana
import project.rpg.skills.SkillType
import project.rpg.skills.magic.MagicSkillBase

class GiganticThunder : MagicSkillBase() {

    init {
        name = SkillType.GIGANTIC_THUNDER.skillName
        description = "적에게 번개를 날린다. 적중시 몹에게 스턴 2초와 화상 5초, 감전 5초를 부여하며 방어력이 4초동안  50% 감소한다."
        circle = 4
        needMana = 12
    }

    @skill(name = "shock_wave")
    override fun onEnable(player: Player, action: Action?) {
        val mana = player.mana
        var location = player.location

        if (mana.useMana(needMana)) {
            if (player.getTargetBlock(30) != null) {
                location = player.getTargetBlock(30)!!.location
            }

            player.world.spawnEntity(location, EntityType.LIGHTNING, CreatureSpawnEvent.SpawnReason.CUSTOM)
            player.world.spawnParticle(Particle.ELECTRIC_SPARK, player.location, 100, 0.25, 0.75, 0.25, 0.1)

            player.world.spawnParticle(Particle.ELECTRIC_SPARK, player.location, 100, 0.25, 0.5, 0.25, 0.1)
        }
    }
}