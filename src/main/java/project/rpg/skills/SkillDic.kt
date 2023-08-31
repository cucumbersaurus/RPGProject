package project.rpg.skills

import org.bukkit.entity.Player
import project.rpg.skills.base.SkillBase
import project.rpg.skills.magic.earth.Binding
import project.rpg.skills.magic.electricity.*
import project.rpg.skills.magic.fire.*
import project.rpg.skills.magic.water.*
import project.rpg.skills.magic.wind.HeavenWing
import project.rpg.skills.magic.wind.Tempest
import project.rpg.skills.tmp.ShoonBow
import project.rpg.skills.tmp.TpArrow

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

            SkillType.PURE_SHIELD.skillName -> {
                PureShield()
            }

            SkillType.WATER_ARROW.skillName -> {
                WaterArrow()
            }

            SkillType.FROZEN_TRACE.skillName -> {
                FrozenTrace()
            }

            SkillType.ICE_SPEAR.skillName -> {
                IceSpear()
            }

            SkillType.ETERNAL_FROST.skillName -> {
                EternalFrost()
            }

            SkillType.LIGHTNING_STORM.skillName -> {
                LightningStorm()
            }

            SkillType.THUNDER_CHARGING.skillName -> {
                ThunderCharging()
            }

            SkillType.LIGHTNING_CHAIN.skillName -> {
                LightningChain()
            }

            SkillType.SHOCK_WAVE.skillName -> {
                ShockWave()
            }

            SkillType.GIGANTIC_THUNDER.skillName -> {
                GiganticThunder()
            }

            SkillType.HEAVEN_WING.skillName -> {
                HeavenWing()
            }

            SkillType.TEMPEST.skillName -> {
                Tempest()
            }

            SkillType.BINDING.skillName -> {
                Binding()
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