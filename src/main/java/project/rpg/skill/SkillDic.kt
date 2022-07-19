package project.rpg.skill

import org.bukkit.entity.Player
import project.rpg.skill.base.SkillBase
import project.rpg.skill.magic.fire.FlameBurst
import project.rpg.skill.magic.fire.MeteorStrike
import project.rpg.skill.tmp.ShoonBow
import project.rpg.skill.tmp.TpArrow

object SkillDic {
    //이거 SkillCommand 에서 switch 문 말고 if로 단순화 할려고
    private val _skillList = ArrayList<String>() //전체 스킬 이름 리스트

    fun makeSkill(player: Player?, skillName: String): SkillBase? {
        return when (skillName) {
            SkillType.METEOR_STRIKE.skillName -> {
                MeteorStrike(player)
            }
            SkillType.FLAME_BURST.skillName -> {
                FlameBurst(player)
            }
            SkillType.TP_ARROW.skillName -> {
                TpArrow(player)
            }
            SkillType.SHOONBOW.skillName -> {
                ShoonBow(player)
            }
            else -> null
        }
    }

    fun addAll() {
        _skillList.add(SkillType.METEOR_STRIKE.skillName)
        _skillList.add(SkillType.FLAME_BURST.skillName)
        _skillList.add(SkillType.TP_ARROW.skillName)
        _skillList.add(SkillType.SHOONBOW.skillName)
    }

    fun isExist(skillName: String): Boolean {
        return _skillList.contains(skillName)
    }

    val skillList: List<String>
        get() = _skillList
}