package project.rpg.skill.base;

public abstract class ActiveSkillBase extends SkillBase{

    @Override
    public void onClick() {
        if (this.coolTime == 0) {
            this.onEnable();
            this.coolTime = this.skillTime;
        } else {
            this.sendActionBar(this.player);
        }
    }

}
