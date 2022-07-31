package project.rpg.items.weapon;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import project.rpg.items.ItemBase;
import project.rpg.skill.base.SkillBase;

public abstract class WeaponBase extends ItemBase {

    protected SkillBase skill;
    public abstract void onEnable(Action action, Player player);
}
