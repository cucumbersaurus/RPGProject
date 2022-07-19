package project.rpg.skill.base;

import org.bukkit.entity.Player;

public abstract class ActiveSkillBase extends SkillBase{

    @Override
    public void onEnable(Player player) {
        if (this._coolTime == 0) {
            this.onEnable(player);
            this._coolTime = this._skillTime;
        } else {
            this.sendActionBar(this._player);
        }
    }

}
