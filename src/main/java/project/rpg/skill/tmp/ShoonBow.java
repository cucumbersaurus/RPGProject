package project.rpg.skill.tmp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import project.rpg.skill.SkillType;
import project.rpg.skill.base.MagicSkillBase;

public class ShoonBow extends MagicSkillBase {
    @Override
    public void onEnable() {
        if (this._skillTime==0) {
            _player.getWorld().spawnParticle(Particle.GLOW, _player.getLocation(), 25, 0.5, 1, 0.5, 0.7);
            Location location = null;
            for(Player all : Bukkit.getOnlinePlayers()) {
                all.playSound(_player.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 0.6f, 2);
                if (!all.equals(_player)) {
                    if (location == null || _player.getLocation().distance(all.getLocation()) < _player.getLocation().distance(location)) {
                        location = all.getLocation();
                    }

                    location.subtract(location.getDirection()).subtract(location.getDirection());

                    _player.teleport(location);
                    this._skillTime = this._coolTime;
                }
            }
        }
    }

    public ShoonBow(Player p){
        this._player = p;
        this._name = SkillType.SHOONBOW.getSkillName();
        this._description = "사람에게 술식을 새겼다.";
        this.circle = 6;
        this.needMana = 0;
    }
}
