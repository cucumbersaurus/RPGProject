package project.rpg.player.info

import kotlinx.serialization.Serializable
import org.bukkit.entity.Player
import org.bukkit.event.Event
import project.rpg.skill.base.SkillBase
import java.util.*

@Serializable
data class Skill(
    private val player: Player,    //스킬 사용시 필요
    private val skillMap: MutableMap<String?, SkillBase> = HashMap()
) {

    fun addSkill(skill: SkillBase) { //스킬 추가하기
        skillMap[Objects.requireNonNull(skill).name] = skill
    }

    //전체 스킬 목록 가져오기
    val skillList: List<SkillBase>
        get() =//전체 스킬 목록 가져오기
            ArrayList(skillMap.values)

    fun getSkill(skillName: String?): SkillBase? { //특정 스킬 가져오기
        return skillMap[skillName]
    }

    fun executeSkill(event: Event?, skillName: String?) {
        //구현 예정
    }
}