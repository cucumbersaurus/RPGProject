package project.rpg.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import static project.rpg.player.info.Skill.skills;

public class PlayerRightClickListener implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();

        if (action==Action.RIGHT_CLICK_AIR||action==Action.RIGHT_CLICK_BLOCK) {
            if (player.getItemInHand().getType()== Material.STICK) {
                event.setCancelled(true);
                skills.get(player.getName()).useSkill("메테오 스트라이크");
            }
        }
    }
}
