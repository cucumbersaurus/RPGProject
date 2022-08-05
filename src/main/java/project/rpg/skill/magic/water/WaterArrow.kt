package project.rpg.skill.magic.water

import org.bukkit.Bukkit
import org.bukkit.Particle
import org.bukkit.entity.AbstractArrow
import org.bukkit.entity.Arrow
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import project.rpg.annotation.skill
import project.rpg.player.User
import project.rpg.skill.SkillType
import project.rpg.skill.magic.MagicSkillBase

class WaterArrow : MagicSkillBase() {
    @skill(name = "water_arrow")
    override fun onEnable(player: Player, action: Action?) {
        val mana = User.getPlayer(player).mana

        if (mana.useMana(needMana)) {
            val arrow = player.launchProjectile(Arrow::class.java)
            arrow.knockbackStrength = 1
            arrow.setGravity(true)
            arrow.pickupStatus = AbstractArrow.PickupStatus.DISALLOWED
            arrow.velocity = player.location.direction.multiply(2)
            //TODO : 맞았을 때 스턴 0.2초

            player.world.spawnParticle(Particle.WATER_DROP, player.location, 100, 0.25, 3.0, 0.25, 0.1)

            val pluginManager = Bukkit.getPluginManager()
            try {
                Class.forName("project.rpg.Rpg")
            } catch (exception: ClassNotFoundException) {
                return
            }
            val plugin = pluginManager.getPlugin("Rpg")

            plugin?.let {
                Bukkit.getScheduler().scheduleSyncDelayedTask(it, {
                    while (!arrow.isOnGround) {
                        arrow.world.spawnParticle(Particle.WATER_DROP, arrow.location, 100, 0.25, 3.0, 0.25, 0.1)
                    }
                }, 10)
            }

        }
    }

    init {
        _name = SkillType.ICE_SPEAR.skillName
        _description = "몹에게 적중 시 넉백 1칸 과 스턴 0.2초"
        circle = 2
        needMana = 5
    }
}