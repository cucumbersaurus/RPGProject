package project.rpg.skill.magic.fire

import org.bukkit.Bukkit
import org.bukkit.Particle
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import project.rpg.annotation.skill
import project.rpg.effect.Burns
import project.rpg.extensions.mana
import project.rpg.skill.SkillType
import project.rpg.skill.magic.MagicSkillBase

class FlareClock : MagicSkillBase() {

    init {
        name = SkillType.FLARE_CLOCK.skillName
        description = "적중시 적에게 붉은색 발광을 5초 부여하며 5초 후에 적 근처 2칸 범위의 폭발이 일어나며 적에게는 화상 10초를 부여하며 공격력이 20% 감소합니다."
        circle = 5
        needMana = 20
    }

    @skill(name = "flare_clock")
    override fun onEnable(player: Player, action: Action?) {
        val mana = player.mana

        if (mana.useMana(needMana)) {
            val entity = player.getTargetEntity(40, false)
            if (entity!=null) {
                entity.isGlowing = true
                val pluginManager = Bukkit.getPluginManager()
                try {
                    Class.forName("project.rpg.Rpg")
                } catch (e : ClassNotFoundException) {
                    return
                }
                val plugin = pluginManager.getPlugin("Rpg")

                plugin?.let {
                    Bukkit.getScheduler().runTaskLater(it, Runnable {
                        entity.isGlowing = false
                        Burns(entity as LivingEntity?,10)
                        entity.world.createExplosion(player,entity.location, 32f,true,true)
                    }, (20 * 5).toLong())
                }
                //TODO : 공격력 20% 감소
                player.world.spawnParticle(Particle.FLAME, player.location, 100, 0.25, 3.0, 0.25, 0.1)
            }
        }
    }
}