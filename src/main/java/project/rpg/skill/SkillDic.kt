package project.rpg.skill

import org.bukkit.entity.Player
import project.rpg.skill.base.SkillBase
import project.rpg.skill.magic.fire.FlameBurst
import project.rpg.skill.magic.fire.MeteorStrike
import project.rpg.skill.tmp.ShoonBow
import project.rpg.skill.tmp.TpArrow

object SkillDic {
    //이거 SkillCommand 에서 switch 문 말고 if로 단순화 할려고
    val skillList = ArrayList<String>() //전체 스킬 이름 리스트
    fun makeSkill(player: Player?, skillName: String): SkillBase? {
        return when (skillName) {
            SkillType.METEOR_STRIKE.skillName -> {
                MeteorStrike()
            }
            SkillType.FLAME_BURST.skillName -> {
                FlameBurst()
            }
            SkillType.TP_ARROW.skillName -> {
                TpArrow()
            }
            SkillType.SHOONBOW.skillName -> {
                ShoonBow(player)
            }
            else -> null
        }
    }

    fun addAll() {
        skillList.add(SkillType.METEOR_STRIKE.skillName)
        skillList.add(SkillType.FLAME_BURST.skillName)
        skillList.add(SkillType.TP_ARROW.skillName)
        skillList.add(SkillType.SHOONBOW.skillName)
    }

    fun isExist(skillName: String): Boolean {
        return skillList.contains(skillName)
    }
}