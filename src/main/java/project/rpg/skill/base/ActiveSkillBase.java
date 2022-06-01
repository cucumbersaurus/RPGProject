package project.rpg.skill.base;

public abstract class ActiveSkillBase extends SkillBase{

    @Override
    public void onClick() {
        if (this._coolTime == 0) {
            this.onEnable();
            this._coolTime = this._skillTime;
        } else {
            this.sendActionBar(this._player);
        }
    }

}
