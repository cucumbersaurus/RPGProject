package project.rpg.skill.base;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

import static net.kyori.adventure.text.Component.text;

public abstract class SkillBase implements Operable {

    public String _name;
    public String _description;
    protected Player _player;
    protected int _skillTime;
    protected int _coolTime;

    public void onEnable(){}
    public void onEnable(Player player, Action action) {}

    public void sendActionBar(Player p) { p.sendActionBar(text(this._coolTime/20 + "초 남음")); }
    public void minTime() {
        if (this._coolTime >0) { --this._coolTime; }
    }

    public String getName() { return _name; }

}
