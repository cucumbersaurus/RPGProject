package project.rpg.skill.base;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;


public abstract class MagicSkillBase extends ActiveSkillBase{

    protected short circle;
    protected int needMana;

    public int getNeedMana() {return this.needMana;}
    public void onEnable(Player player, Action action) {}

}
