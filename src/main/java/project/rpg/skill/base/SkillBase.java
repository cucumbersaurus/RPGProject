package project.rpg.skill.base;

import org.bukkit.entity.Player;

public abstract class SkillBase implements Operable {

    public String name;
    public String description;
    protected Player player;
    protected int skillTime;
    protected int coolTime;

    public void sendActionBar(Player p) { p.sendActionBar(this.coolTime + "초 남음"); }

    public void minTime() {
        if (this.coolTime>0) { --this.coolTime; }
    }

}
