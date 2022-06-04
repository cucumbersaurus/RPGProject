package project.rpg.skill.magic.fire;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import project.rpg.skill.SkillType;
import project.rpg.skill.base.MagicSkillBase;

public class MeteoStrike extends MagicSkillBase {

    @Override
    public void onEnable() {
        for (Entity entity : _player.getNearbyEntities(7, 7, 7)) {
            if(entity instanceof Player){
                continue;
            }

            if (entity instanceof LivingEntity) {
                LivingEntity all = (LivingEntity) entity;
                all.getWorld().spawnParticle(Particle.FLAME, all.getLocation(), 400, 0.25, 3, 0.25, 0.1);

                if (!_player.equals(all)) {
                    if (all.getHealth() > 10) {
                        all.damage(10);
                    } else {
                        all.damage(all.getHealth());
                    }
                    all.setFireTicks(20 * 2);
                    all.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,20,2,true));
                }
            }
        }
        for (Player p : _player.getLocation().getNearbyPlayers(7, 7, 7)) {
            p.playSound(_player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.6f, 1);
        }
        _player.getWorld().spawnParticle(Particle.FLAME, _player.getLocation(), 100, 0.25, 3, 0.25, 0.1);
    }

    public MeteoStrike(Player player) {
        this._player = player;
        this._name = SkillType.METEOR_STRIKE.getSkillName();
        this._description = "반경 7블럭에 적들에게 메테오를 난사한다. 범위 내의 적들에게 스턴 1초와 방어력 50% 감소를 2초간 부여하며 화상효과도 부여한다.";
        this.circle = 4;
        this.needMana = 10;
    }

}
