package project.rpg.skill.magic.electricity

import io.github.monun.heartbeat.coroutines.HeartbeatScope
import kotlinx.coroutines.launch
import org.bukkit.Bukkit
import org.bukkit.Particle
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import project.rpg.annotation.skill
import project.rpg.effect.Damage
import project.rpg.effect.ElectricShock
import project.rpg.extensions.mana
import project.rpg.skill.SkillType
import project.rpg.skill.magic.MagicSkillBase

class LightningChain : MagicSkillBase() {

    init {
        _name = SkillType.LIGHTNING_CHAIN.skillName
        _description = "몹에게 번개를 날리는데 적중시 주변 다른 몹에게도 동일한 양의 데미지와 감전 2초를 부여한다. (최대 4회까지)"
        circle = 2
        needMana = 7
    }

    @skill(name = "lightning_chain")
    override fun onEnable(player: Player, action: Action?) {
        val mana = player.mana

        if (mana.useMana(needMana)) {
            val entity = player.getTargetEntity(20, false)

            val pluginManager = Bukkit.getPluginManager()
            try {
                Class.forName("project.rpg.Rpg")
            } catch (exception: ClassNotFoundException) {
                return
            }

            if (entity!=null && entity is LivingEntity) {
                var locationEntity = entity
                Damage(entity,4)
                ElectricShock(entity,2)
                entity.world.spawnParticle(Particle.SPIT, entity.location, 50, 0.25, 0.5, 0.25, 0.1)

                for (i in 0..3) {
                    HeartbeatScope().launch {
                        for (target in locationEntity?.getNearbyEntities(8.0, 8.0, 8.0)!!) {
                            if (target is LivingEntity&&target!=player) {
                                Damage(target,4)
                                ElectricShock(target,2)
                                target.world.spawnParticle(Particle.SPIT, target.location, 50, 0.25, 0.5, 0.25, 0.1)
                                locationEntity = target
                                break
                            }
                        }
                    }
                }
            }
        }
    }
}