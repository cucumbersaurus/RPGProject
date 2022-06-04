package project.rpg.skill;

import org.bukkit.entity.Player;
import project.rpg.skill.base.SkillBase;
import project.rpg.skill.magic.fire.MeteoStrike;

import java.util.ArrayList;

public class SkillDic { //이거 SkillCommand에서 switch문 말고 if로 단순화 할려고

    private static ArrayList<String> _skillList = new ArrayList<>();  //스킬 이름 리스트

    public static SkillBase getSkill(Player player, String skillName) {
        if(skillName.equals(getName(new MeteoStrike(null)))) {
            return new MeteoStrike(player);
        }
        return null;
    }

    public static void addAll() {
        _skillList.add(getName(new MeteoStrike(null))); //ㅇㄴ ㅋㅋㅋㅋ 내가 생각해도 뭐하는거지
    }

    public static String getName(SkillBase skill) {  //이거 이름 가져오는거 생각이 안나서 그냥 이렇게 함ㅋㅋㅋㅋ 좋은 아이디어 있을테니까 그거로 바꾸셈
        if (skill instanceof MeteoStrike) {          //SkillBase 가 abstract 여서 ㅋㅋㅋㅋ static 은 상속이 안되니까
            return new MeteoStrike(null)._name;
        }
        return "";
    }

    public static boolean isExist(String skillName) { return _skillList.contains(skillName); }

}