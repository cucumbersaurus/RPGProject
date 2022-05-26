package project.rpg.skill.base;

import org.bukkit.entity.Player;
import project.rpg.skill.Operable;

public abstract class SkillBase implements Operable {

    public String name;
    public String description;
    protected Player player;
    protected int skillTime;
    protected int coolTime;

    public void onClick(){}
    public void onClick(Player p){}
    public void onClick(Player p,Player t){}

    public void sendActionBar(Player p) { p.sendActionBar(this.coolTime + "초 남음"); }

    public void minTime() {
        if (this.coolTime>0) { --this.coolTime; }
    }

}
