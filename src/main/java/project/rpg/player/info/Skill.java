package project.rpg.player.info;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import project.rpg.skill.base.SkillBase;

import java.util.*;

public class Skill implements ConfigurationSerializable {

    private final Map<String, SkillBase> _skillMap = new HashMap<>();
    private final Player _player;//스킬 사용시 필요

    public void addSkill(SkillBase skill){ //스킬 추가하기
        _skillMap.put(Objects.requireNonNull(skill)._name, skill);
    }


    public List<SkillBase> getSkillList(){ //전체 스킬 목록 가져오기
        return new ArrayList<>(_skillMap.values());
    }

    public Map<String, SkillBase> getSkillMap(){
        return _skillMap;
    }

    public SkillBase getSkill(String skillName) { //특정 스킬 가져오기
        return _skillMap.get(skillName);
    }

    public Skill(Player player) {
        _player = player;
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        return new HashMap<>();//구현 예정
    }

    public void executeSkill(Event event, String skillName){
        //구현 예정
    }

}
