package project.rpg.player.info;

import org.bukkit.entity.Player;
import project.rpg.skill.base.SkillBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Skill {

    private static final Map< Player, Skill> _skills = new HashMap<>();

    private final Map<String, SkillBase> _skillMap = new HashMap<>();  //스킬이 하나 말고 여러개이기 때문   위에 uuid는 나중에
    private final Player _player;

    public static List<SkillBase> getSkillList(Player player){ //전체 스킬 목록 가져오기
        Skill skills = _skills.get(player);

        return (List<SkillBase>) skills.getSkillMap().values();
    }

    public Map<String, SkillBase> getSkillMap(){
        return _skillMap;
    }

    public static SkillBase getSkill(Player player,String skillName){ //특정 스킬 가져오기
        Map<String, SkillBase> skills = Skill._skills.get(player).getSkillMap();
        if (skills.containsKey(skillName)) {
            return skills.get(skillName);
        }
        return null;
    }

    public SkillBase getSkill(String skillName) {
        return getSkill(_player,skillName);
    }

    public static void addSkill(Player player, SkillBase skill){  //스킬 추가하기 static  addPlayer 동합
        Map<String, SkillBase> skills = _skills.get(player).getSkillMap();
        if (_skills.containsKey(player)) {
            skills.put(skill._name, skill);
        } else {
            if (skill!=null) {
                skills = new HashMap<>();
                skills.put(skill._name, skill);
            }
        }
    }

    public void addSkill(SkillBase skillBase) {  //스킬 추가하기 not static
        addSkill(_player,skillBase);
    }

    public static void removePlayer(Player player){  //플레이어 삭제
        _skills.remove(player);
    }

    public Skill(Player player, SkillBase skill) {
        _skills.put(player, this);
        _player = player;
        addSkill(player, skill);
    }
}
