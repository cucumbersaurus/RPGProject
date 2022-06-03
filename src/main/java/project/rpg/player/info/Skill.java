package project.rpg.player.info;

import org.bukkit.entity.Player;
import project.rpg.skill.base.MagicSkillBase;
import project.rpg.skill.base.SkillBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Skill {

    private static final Map<Player, SkillBase > _skills = new HashMap<>();
    private final ArrayList<SkillBase> _skillBaseBases;//이거 왜 arraylist 냐 hashmap쓰지

    private int _needMana;

    public static SkillBase getSkill(Player player){
        return _skills.get(player);
    }

    public static void setSkill(Player player, SkillBase skill){
        _skills.put(player, skill);
    }

    public SkillBase getSkill(String s) {
        for(SkillBase skillBase : _skillBaseBases) {
            if(skillBase._name.equals(s)) return skillBase;
        }
        return null;
    }

    public boolean hasSKill(String s) {
        for (SkillBase skillBase : _skillBaseBases) {
            if (skillBase._name.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public void addSkill(SkillBase s) {
        _skillBaseBases.add(s);
    }

    public void useSkill(Player player,  String s) {
        SkillBase skill = getSkill(s);
        if(skill == null) return;

        if (skill instanceof MagicSkillBase) {
            int mana = ((MagicSkillBase) skill).getNeedMana();
            if(Mana.useMana(player, mana)){
                skill.onEnable();
            }
        } else {
            skill.onEnable();
        }
    }

    public Skill(Player player, SkillBase skill) {
        _skills.put(player, skill);
        _skillBaseBases = new ArrayList<>();
    }
}
