package project.rpg.skill.magic.fire;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import project.rpg.skill.base.MagicSkillBase;

public class MeteoStrike extends MagicSkillBase {

    @Override
    public void onEnable() {
        for (Entity entity : player.getNearbyEntities(7,7,7)) {

            if (entity instanceof LivingEntity) {
                LivingEntity all = (LivingEntity) entity;
                all.getWorld().spawnParticle(Particle.FLAME, all.getLocation(), 400, 0.25, 3, 0.25, 0.1);

                if (!player.equals(all)) {
                    if (all.getHealth() > 10) {
                        all.damage(10);
                    } else {
                        all.damage(all.getHealth());
                    }
                    all.setFireTicks(20 * 2);
                    all.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,20*1,2,true));
                }
            }
        }
        for (Player p : player.getLocation().getNearbyPlayers(7,7,7)) {
            p.playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 0.6f, 1);
        }
    }

    public MeteoStrike(Player p) {
        this.player = p;
        this.name = "메테오 스트라이크";
        this.description = "반경 7블럭에 적들에게 메테오를 난사한다. 범위 내의 적들에게 스턴 1초와 방어력 50% 감소를 2초간 부여하며 화상효과도 부여한다.";
        this.circle = 4;
        this.needMana = 10;
    }

}
