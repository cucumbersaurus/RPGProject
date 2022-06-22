package project.rpg.player.info;

import org.bukkit.entity.Player;
import project.rpg.skill.base.SkillBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Skill {

    private static final Map< Player, Map<String,SkillBase> > _skills = new HashMap<>();  //Map<String,SkillBase>는 저장할때 Skill로 하면 주소값이 저장되서 저러게하면 스킬 목록 알수있음
    private final Map<String, SkillBase> _skillMap = new HashMap<>();  //스킬이 하나 말고 여러개이기 때문   위에 uuid는 나중에
    private final Player _player;

    public static SkillBase[] getSkills(Player player){ //전체 스킬 목록 가져오기
        Map<String, SkillBase> skills = _skills.get(player);
        ArrayList<SkillBase> retSkills = new ArrayList<>();

        for (String key : skills.keySet()) {
            retSkills.add(skills.get(key));
        }

        return (SkillBase[]) retSkills.toArray();
    }

    public static SkillBase getSkill(Player player,String skillName){ //특정 스킬 가져오기
        Map<String, SkillBase> skills = _skills.get(player);

        if (skills.containsKey(skillName)) {
            return skills.get(skillName);
        }
        return null;
    }

    public SkillBase getSkill(String skillName) {
        return getSkill(_player,skillName);
    }

    public static void addSkill(Player player, SkillBase skill){  //스킬 추가하기 static  addPlayer 동합
        if (_skills.containsKey(player)) {
            Map<String, SkillBase> skills = _skills.get(player);
            skills.put(skill._name, skill);
            _skills.put(player, skills);
        } else {
            if (skill!=null) {
                Map<String, SkillBase> skills = new HashMap<>();
                skills.put(skill._name, skill);
                _skills.put(player, skills);
            } else {
                _skills.put(player, new HashMap<>());
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
        _player = player;
        addSkill(player,skill);
    }
    public Skill(Player player) {
        _player = player;
    }

}
