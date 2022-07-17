package project.rpg.skill.magic.fire

import org.bukkit.entity.Player
import project.rpg.skill.SkillType
import project.rpg.skill.base.MagicSkillBase

class FlameBurst(player: Player?) : MagicSkillBase() {
    override fun onEnable() {
        //val fireball = _player.launchProjectile(Fireball, this._player.location.direction)
    }

    init {
        _player = player
        _name = SkillType.METEOR_STRIKE.skillName
        _description = "몹에게 화염구를 발사한다. 적중 시 몹에게 화상 효과를 부여하며 방어력을 2초간 무시한다."
        circle = 3
        needMana = 5
    }
}