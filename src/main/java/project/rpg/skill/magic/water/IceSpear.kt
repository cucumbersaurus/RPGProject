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

class IceSpear : MagicSkillBase() {
    @skill(name = "ice_spear")
    override fun onEnable(player: Player, action: Action?) {
        val mana = player.mana

        if (mana.useMana(needMana)) {
            val arrow = player.launchProjectile(Arrow::class.java)
            arrow.knockbackStrength = 5
            arrow.setGravity(true)
            arrow.damage = 7.0
            arrow.pickupStatus = AbstractArrow.PickupStatus.DISALLOWED

            player.world.spawnParticle(Particle.WATER_DROP, player.location, 100, 0.25, 3.0, 0.25, 0.1)

            val pluginManager = Bukkit.getPluginManager()
            try {
                Class.forName("project.rpg.Rpg")
            } catch (exception: ClassNotFoundException) {
                return
            }
            val plugin = pluginManager.getPlugin("Rpg")

            arrow.setMetadata(SkillType.ICE_SPEAR.skillName, FixedMetadataValue(plugin!!,true))

            arrow.velocity = player.location.direction.multiply(3)
        }
    }

    init {
        _name = SkillType.ICE_SPEAR.skillName
        _description = "적에게 얼음 송곳을 발사한다. 투사체가 적중 시 적중한 적에서 스턴 3초와 슬로우 5초를 건다. 만일 적중 실패시 주변 반경 2블럭의 적에게 슬로우 2초를 준다."
        circle = 3
        needMana = 5
    }
}