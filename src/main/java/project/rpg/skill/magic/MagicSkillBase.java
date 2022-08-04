package project.rpg.skill.magic;

import org.bukkit.Bukkit;
import project.rpg.skill.base.ActiveSkillBase;

public abstract class MagicSkillBase extends ActiveSkillBase {

    protected short circle;
    protected int needMana;

    public int getNeedMana() {return this.needMana;}

}
