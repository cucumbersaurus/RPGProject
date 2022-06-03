package project.rpg.player.info;

import org.bukkit.entity.Player;
import project.rpg.skill.base.SkillBase;

import java.util.HashMap;
import java.util.Map;

public class Skill {

    private static final Map<Player, SkillBase > _skills = new HashMap<>();
    private static final Map<Player, Skill> _skillMap = new HashMap<>();
    private SkillBase _skillBase;
    public static SkillBase getSkillBase(Player player){
        return _skills.get(player);
    }


    public static void setSkill(Player player, SkillBase skill){
        _skills.put(player, skill);
    }

    public SkillBase getSkillBase(){
        return _skillBase;
    }

    public void setSkillBase(SkillBase skillBase) {
        _skillBase = skillBase;
    }

    public static void addPlayer(Player player, Skill skill){
        _skillMap.put(player, skill);
    }

    public static void removePlayer(Player player){
        _skillMap.remove(player);
    }

    public static Skill getSkill(Player player){
        return _skillMap.get(player);
    }

    public Skill(Player player, SkillBase skill) {
        addPlayer(player, this);
        _skills.put(player, skill);
    }
}
