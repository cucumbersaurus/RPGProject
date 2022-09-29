package project.rpg.skill.magic.water

import org.bukkit.Bukkit
import org.bukkit.Particle
import org.bukkit.entity.AbstractArrow
import org.bukkit.entity.Arrow
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.metadata.FixedMetadataValue
import project.rpg.annotation.skill
import project.rpg.extensions.mana
import project.rpg.skill.SkillType
import project.rpg.skill.magic.MagicSkillBase

class WaterArrow : MagicSkillBase() {

    init {
        _name = SkillType.WATER_ARROW.skillName
        _description = "몹에게 적중 시 넉백 1칸 과 스턴 0.2초"
        circle = 2
        needMana = 5
    }

    @skill(name = "water_arrow")
    override fun onEnable(player: Player, action: Action?) {
        val mana = player.mana

        if (mana.useMana(needMana)) {
            val arrow = player.launchProjectile(Arrow::class.java)
            arrow.knockbackStrength = 1
            arrow.setGravity(true)
            arrow.pickupStatus = AbstractArrow.PickupStatus.DISALLOWED
            arrow.damage = 3.0
            player.world.spawnParticle(Particle.WATER_DROP, player.location, 100, 0.25, 3.0, 0.25, 0.1)

            val pluginManager = Bukkit.getPluginManager()
            try {
                Class.forName("project.rpg.Rpg")
            } catch (exception: ClassNotFoundException) {
                return
            }
            val plugin = pluginManager.getPlugin("Rpg")

            arrow.setMetadata(SkillType.WATER_ARROW.skillName,FixedMetadataValue(plugin!!,true))

            arrow.velocity = player.location.direction.multiply(2)

        }
    }
}