package project.rpg.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import project.rpg.Rpg;
import project.rpg.items.Wand;
import project.rpg.manager.ItemManager;
import project.rpg.player.PlayerInformation;

import static project.rpg.player.info.Skill._skills;

public class PlayerItemUseEventListener implements Listener {

    private final Rpg _plugin;

    public PlayerItemUseEventListener(Rpg plugin) {
        _plugin = plugin;
    }

    @EventHandler
    public void itemUseEvent(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(event.getItem()!=null) {
            if (ItemManager.isEquals(event.getItem(), Wand._wand)) {
                if(event.getAction().isRightClick()){
                    if(PlayerInformation.getMana(player)>=10){
                    Location location = player.getLocation();
                        if(player.getTargetBlock(30)!=null){
                            location = player.getTargetBlock(30).getLocation();
                        }
                        player.getWorld().spawnEntity(location, EntityType.LIGHTNING);
                        PlayerInformation.useMana(player,10);
                        _plugin._actionBar.updateActionBar();
                    }
                }
            }
        }

        Action action = event.getAction();
        if (action==Action.RIGHT_CLICK_AIR||action==Action.RIGHT_CLICK_BLOCK) {
            if (player.getItemInHand().getType()== Material.FIRE_CHARGE) {
                event.setCancelled(true);
                _skills.get(player.getName()).useSkill("메테오 스트라이크");
            }
        }

    }
}