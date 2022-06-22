package project.rpg.skill

import org.bukkit.entity.Player
import project.rpg.skill.base.SkillBase
import project.rpg.skill.magic.fire.MeteorStrike
import project.rpg.skill.tmp.ShoonBow
import project.rpg.skill.tmp.TpArrow

object SkillDic {
    //이거 SkillCommand 에서 switch 문 말고 if로 단순화 할려고
    private val _skillList = ArrayList<String>() //전체 스킬 이름 리스트

    fun makeSkill(player: Player?, skillName: String): SkillBase? {
        if (skillName == SkillType.METEOR_STRIKE.skillName) {
            return MeteorStrike(player)
        } else if (skillName == SkillType.TP_ARROW.skillName) {
            return TpArrow(player)
        } else if (skillName == SkillType.SHOONBOW.skillName) {
            return ShoonBow(player)
        }
        return null
    }

    fun addAll() {
        _skillList.add(SkillType.METEOR_STRIKE.skillName)
        _skillList.add(SkillType.TP_ARROW.skillName)
        _skillList.add(SkillType.SHOONBOW.skillName)
    }

    fun isExist(skillName: String): Boolean {
        return _skillList.contains(skillName)
    }

    val skillList: List<String>
        get() = _skillList
}