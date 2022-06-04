package project.rpg.skill.base;

import org.bukkit.entity.Player;

public abstract class SkillBase implements Operable {

    public String _name;
    public String _description;
    protected Player _player;
    protected int _skillTime;
    protected int _coolTime;

    public String getName() { return _name; }

    public void sendActionBar(Player p) { p.sendActionBar(this._coolTime + "초 남음"); }
    public void minTime() {
        if (this._coolTime >0) { --this._coolTime; }
    }

    //public void useSkill(Player player, String skillName) {

}
