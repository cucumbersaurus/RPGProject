package project.rpg.player.info;

import org.bukkit.entity.Player;
import project.rpg.skill.base.MagicSkillBase;
import project.rpg.skill.base.SkillBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Skill {

    public static Map<String, Skill> skills = new HashMap<>();

    private ArrayList<SkillBase> skillBases;
    private int mana;
    private int maxMana;

    public SkillBase getSkill(String s) {
        for (int i = 0; i < skillBases.size(); i++) {
            if (skillBases.get(i).name.equals(s)) {
                return skillBases.get(i);
            }
        }
        return null;
    }

    public void addSkill(SkillBase s) {
        skillBases.add(s);
    }

    public int getMana() {
        return mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void useSkill(String s) {
        SkillBase skill = getSkill(s);
        if (skill instanceof MagicSkillBase) {
            int m = ((MagicSkillBase) skill).getNeedMana();
            if (this.mana >= m) {
                this.mana -= m;
                skill.onEnable();
            }
        } else {
            skill.onEnable();
        }
    }

    public Skill(Player p) {
        skillBases = new ArrayList<>();
        maxMana = 100;
        mana = maxMana;
    }

}
