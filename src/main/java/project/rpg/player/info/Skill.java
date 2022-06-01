package project.rpg.player.info;

import project.rpg.skill.base.MagicSkillBase;
import project.rpg.skill.base.SkillBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Skill {

    public static final Map<String, Skill> _skills = new HashMap<>();

    private final ArrayList<SkillBase> _skillBases;//이거 왜 arraylist 냐 hashmap쓰지
    private int _mana;
    private int _maxMana;

    public SkillBase getSkill(String s) {
        for(SkillBase skill : _skillBases) {
            if(skill._name.equals(s)) return skill;
        }
        return null;
    }

    public boolean hasSKill(String s) {
        for (SkillBase skillBase : _skillBases) {
            if (skillBase._name.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public void addSkill(SkillBase s) {
        _skillBases.add(s);
    }

    public int getMana() {
        return _mana;
    }

    public void plusMana() {
        this._mana++;
    }

    public void useMana(int m) {
        this._mana -= m;
    }

    public int getMaxMana() {
        return _maxMana;
    }

    public void useSkill(String s) {
        SkillBase skill = getSkill(s);
        if(skill == null) return;

        if (skill instanceof MagicSkillBase) {
            int m = ((MagicSkillBase) skill).getNeedMana();
            if (this._mana >= m) {
                this._mana -= m;
                skill.onEnable();
            }
        } else {
            skill.onEnable();
        }
    }

    public Skill() {
        _skillBases = new ArrayList<>();
        _maxMana = 100;
        _mana = _maxMana;
    }
    public Skill(int m) {
        _skillBases = new ArrayList<>();
        _maxMana = m;
        _mana = _maxMana;
    }

}
