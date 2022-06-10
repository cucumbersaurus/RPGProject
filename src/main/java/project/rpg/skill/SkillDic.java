package project.rpg.skill;

import org.bukkit.entity.Player;
import project.rpg.skill.base.SkillBase;
import project.rpg.skill.magic.fire.MeteorStrike;

import java.util.ArrayList;

public class SkillDic { //이거 SkillCommand 에서 switch 문 말고 if로 단순화 할려고

    private static final ArrayList<String> _skillList = new ArrayList<>();  //전체 스킬 이름 리스트

    public static SkillBase makeSkill(Player player, String skillName) {
        if(skillName.equals(SkillType.METEOR_STRIKE.getSkillName())) {
            return new MeteorStrike(player);
        }
        return null;
    }

    public static void addAll() {
        _skillList.add(SkillType.METEOR_STRIKE.getSkillName());
    }

    public static boolean isExist(String skillName) {
        return _skillList.contains(skillName);
    }
}