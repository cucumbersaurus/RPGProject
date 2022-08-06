package project.rpg.skill.magic.electricity

import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import project.rpg.annotation.skill
import project.rpg.effect.Damage
import project.rpg.effect.ElectricShock
import project.rpg.player.User
import project.rpg.skill.SkillType
import project.rpg.skill.magic.MagicSkillBase

class LightningChain : MagicSkillBase() {
    @skill(name = "water_arrow")
    override fun onEnable(player: Player, action: Action?) {
        val mana = User.getPlayer(player).mana

        if (mana.useMana(needMana)) {
            val entity = player.getTargetEntity(10, false)
            if (entity!=null && entity is LivingEntity) {
                Damage(entity,4)
                ElectricShock(entity,2)
                var count = 1
                for (target in entity.getNearbyEntities(4.0,4.0,4.0)) {
                    //TODO : 하는중
                }
            }
        }
    }

    init {
        _name = SkillType.WATER_ARROW.skillName
        _description = "몹에게 적중 시 넉백 1칸 과 스턴 0.2초"
        circle = 2
        needMana = 5
    }
}