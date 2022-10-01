package project.rpg.skill.tmp;

import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import project.rpg.data.structures.ListQueue;
import project.rpg.player.User;
import project.rpg.skill.SkillType;
import project.rpg.skill.magic.MagicSkillBase;

import static net.kyori.adventure.text.Component.text;

public class TpArrow extends MagicSkillBase {
    private final ListQueue<Arrow> thrownArrows = new ListQueue<>();

    @Override
    public void onEnable(@NotNull Player player, @Nullable Action action) {
        if(action.isRightClick()){
            onRightClick(player);
        }
        else{
            onLeftClick(player);
        }
    }

    private void onRightClick(Player player) {
        if (this._coolTime==0) {
            for(Player all : Bukkit.getOnlinePlayers()){
                all.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 0.6f, 2);
            }
            Vector direction = player.getLocation().getDirection();
            Arrow arrow = getNextValidArrow();
            if(arrow==null){
                player.showTitle(Title.title(text(" "), text("소환되어있는 화살이 없습니다!").color(TextColor.color(0xff5555))));
                User.getPlayer(player).getMana().addMana(this.needMana);
                return;
            }
            else {
                arrow.remove();
                player.teleport(arrow.getLocation().setDirection(direction));
                player.getWorld().spawnParticle(Particle.GLOW, player.getLocation(), 100, 0.5, 1, 0.5);
            }
            this._coolTime = this._skillTime;
        }
        player.sendMessage(text("남은 화살 수 : ").append(text(thrownArrows.getSize())));
    }

    public void onLeftClick(Player player) {
        if(thrownArrows.getSize()>=10){
            Arrow poppedArrow = getNextValidArrow();
            if(poppedArrow!=null){
                for(Player all : Bukkit.getOnlinePlayers()){
                    all.spawnParticle(Particle.CLOUD, poppedArrow.getLocation(), 10, 0.02, 0.02, 0.02, 0.1 );
                }
                poppedArrow.remove();
            }
        }

        Arrow arrow = player.launchProjectile(Arrow.class);
        arrow.setKnockbackStrength(3);
        arrow.setGravity(true);
        arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
        arrow.setVelocity(player.getLocation().getDirection().multiply(5));
        thrownArrows.add(arrow);

        player.sendMessage(text("남은 화살 수 : ").append(text(thrownArrows.getSize())));
    }

    private Arrow getNextValidArrow(){
        if(thrownArrows.isEmpty()) return null;
        Arrow arrow = thrownArrows.pop();
        while(!arrow.isValid()){
            arrow=thrownArrows.pop();
        }
        return arrow;
    }

    public TpArrow(){
        this._name = SkillType.TP_ARROW.getSkillName();
        this._description = "특수한 술식으로 마킹된 공간으로 술자를 역소환하는 술법";
        this.needMana = 5;
        this.circle = 6;
    }
}
