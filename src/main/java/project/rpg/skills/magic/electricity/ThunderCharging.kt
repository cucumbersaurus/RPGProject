package project.rpg.skills.magic.electricity

import org.bukkit.Bukkit
import org.bukkit.Particle
import org.bukkit.entity.AbstractArrow
import org.bukkit.entity.Arrow
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.metadata.FixedMetadataValue
import project.rpg.annotation.skill
import project.rpg.player.mana
import project.rpg.skills.SkillType
import project.rpg.skills.magic.MagicSkillBase

class ThunderCharging : MagicSkillBase() {

    init {
        name = SkillType.THUNDER_CHARGING.skillName
        description = "일자로 나가는 공격을 발사한다. 만일 적중시 몹에게 스턴 1초와 자신의 마나 1 을 추가 회복한다."
        circle = 2
        needMana = 5
    }

    @skill(name = "water_arrow")
    override fun onEnable(player: Player, action: Action?) {
        val mana = player.mana

        if (mana.useMana(needMana)) {
            val arrow = player.launchProjectile(Arrow::class.java)
            arrow.knockbackStrength = 2
            arrow.setGravity(true)
            arrow.pickupStatus = AbstractArrow.PickupStatus.DISALLOWED
            arrow.damage = 4.0
            player.world.spawnParticle(Particle.ELECTRIC_SPARK, player.location, 100, 0.25, 3.0, 0.25, 0.1)

            val pluginManager = Bukkit.getPluginManager()
            try {
                Class.forName("project.rpg.Rpg")
            } catch (exception: ClassNotFoundException) {
                return
            }
            val plugin = pluginManager.getPlugin("Rpg")

            arrow.setMetadata(SkillType.THUNDER_CHARGING.skillName, FixedMetadataValue(plugin!!, true))

            arrow.velocity = player.location.direction.multiply(2)
        }
    }
}