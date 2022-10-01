package project.rpg.skill.tmp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import project.rpg.skill.SkillType;
import project.rpg.skill.magic.MagicSkillBase;

public class ShoonBow extends MagicSkillBase {
    @Override
    public void onEnable(@NotNull Player player, @Nullable Action action) {
        if (this._skillTime==0) {
            player.getWorld().spawnParticle(Particle.GLOW, player.getLocation(), 25, 0.5, 1, 0.5, 0.7);
            Location location = null;
            for(Player all : Bukkit.getOnlinePlayers()) {
                all.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 0.6f, 2);
                if (!all.equals(player)) {
                    if (location == null || player.getLocation().distance(all.getLocation()) < player.getLocation().distance(location)) {
                        location = all.getLocation();
                    }

                    location.subtract(location.getDirection()).subtract(location.getDirection());

                    player.teleport(location);
                    this._skillTime = this._coolTime;
                }
            }
        }
    }

    public ShoonBow(Player p){
        this._name = SkillType.SHOONBOW.getSkillName();
        this._description = "사람에게 술식을 새겼다.";
        this.circle = 6;
        this.needMana = 0;
    }
}
