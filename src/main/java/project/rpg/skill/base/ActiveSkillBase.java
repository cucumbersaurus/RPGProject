package project.rpg.skill.base;

import org.bukkit.event.block.Action;

public abstract class ActiveSkillBase extends SkillBase{

    @Override
    public void onEnable(Action action) {
        if (this._coolTime == 0) {
            this.onEnable(action);
            this._coolTime = this._skillTime;
        } else {
            this.sendActionBar(this._player);
        }
    }

}
