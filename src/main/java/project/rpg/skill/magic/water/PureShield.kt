package project.rpg.skill.magic.water

import org.bukkit.Particle
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import project.rpg.annotation.skill
import project.rpg.player.User
import project.rpg.skill.SkillType
import project.rpg.skill.magic.MagicSkillBase

class PureShield : MagicSkillBase() {
    @skill(name = "pure_shield")
    override fun onEnable(player: Player, action: Action?) {
        val mana = User.getPlayer(player).mana

        if (mana.useMana(10)) {
            if (player.health >= 10) {
                player.health = 20.0
            } else {
                player.health = player.health + 10
            }
            //TODO : 방어력 10초 동안 20% 증가
        }
        player.world.spawnParticle(Particle.WATER_DROP, player.location, 100, 0.25, 3.0, 0.25, 0.1)
    }

    init {
        _name = SkillType.FLAME_BURST.skillName
        _description = "플레이어의 방어력을 10초동안 20%올리고 체력 일부를 회복시킨다."
        circle = 2
        needMana = 5
    }
}