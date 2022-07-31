package project.rpg.skill.base;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.jetbrains.annotations.NotNull;

public abstract class ActiveSkillBase extends SkillBase{

    @Override
    public void onEnable(@NotNull Player player, Action action) {
        if (this._coolTime == 0) {
            this.onEnable(player, action);
            this._coolTime = this._skillTime;
        } else {
            this.sendActionBar(player);
        }
    }

}
