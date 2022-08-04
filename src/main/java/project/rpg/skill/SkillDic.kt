package project.rpg.skill

import org.bukkit.entity.Player
import project.rpg.skill.base.SkillBase
import project.rpg.skill.magic.fire.*
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
            SkillType.EXPLOSION.skillName -> {
                Explosion()
            }
            SkillType.LAVA_ZONE.skillName -> {
                LavaZone()
            }
            SkillType.BLAZING_MARK.skillName -> {
                BlazingMark()
            }
            SkillType.INFERNO.skillName -> {
                Inferno()
            }
            SkillType.FLARE_CLOCK.skillName -> {
                FlareClock()
            }
            else -> null
        }
    }

    fun addAll() {
        for (skill in SkillType.values()) {
            skillList.add(skill.skillName)
        }
    }

    fun isExist(skillName: String): Boolean {
        return skillList.contains(skillName)
    }
}