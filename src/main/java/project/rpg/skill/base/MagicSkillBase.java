package project.rpg.skill.base;

public abstract class MagicSkillBase extends ActiveSkillBase{

    protected short circle;
    protected int needMana;

    public int getNeedMana() {return this.needMana;}

}
