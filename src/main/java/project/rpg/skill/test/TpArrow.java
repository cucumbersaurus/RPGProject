package project.rpg.skill.test;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import project.rpg.material.FIFO;
import project.rpg.skill.SkillType;
import project.rpg.skill.base.MagicSkillBase;

public class TpArrow extends MagicSkillBase {
    private FIFO<Arrow> thrownArrows = new FIFO<>();

    @Override
    public void onEnable() {
        if (this._coolTime==0) {
            for(Player all : Bukkit.getOnlinePlayers()){
                all.playSound(_player.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 0.6f, 2);
            }
            Vector direction = _player.getLocation().getDirection();
            if (!thrownArrows.isEmpty()) {
                Arrow arrow = thrownArrows.pop();
                arrow.remove();
                _player.teleport(arrow.getLocation().setDirection(direction));
            }

            _player.getWorld().spawnParticle(Particle.GLOW, _player.getLocation(), 100, 0.5, 1, 0.5);

            this._coolTime = this._skillTime;

        }
    }

    public void onLeftClick() {
        Arrow arrow = _player.launchProjectile(Arrow.class);
        arrow.setKnockbackStrength(3);
        arrow.setGravity(true);
        arrow.setPickupRule(AbstractArrow.PickupRule.DISALLOWED);
        arrow.setVelocity(_player.getLocation().getDirection().multiply(5));
        thrownArrows.add(arrow);
    }

    public TpArrow(Player p){
        this._player = p;
        this._name = SkillType.TP_ARROW.getSkillName();
        this._description = "특수한 술식으로 마킹된 공간으로 술자를 역소환하는 술법";
        this.needMana = 0;
        this.circle = 6;
    }
}
