package project.rpg.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import project.rpg.Rpg;
import project.rpg.items.Wand;
import project.rpg.manager.ItemManager;
import project.rpg.player.info.Mana;
import project.rpg.player.info.Skill;
import project.rpg.skill.SkillType;
import project.rpg.skill.base.SkillBase;

public class PlayerItemUseEventListener implements Listener {

    private final Rpg _plugin;

    public PlayerItemUseEventListener(Rpg plugin) {
        _plugin = plugin;
    }

    @EventHandler
    public void itemUseEvent(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(event.getItem()!=null) {
            if (ItemManager.isEquals(event.getItem(), Wand.getItem())) {
                event.setCancelled(true);
                if(event.getAction().isRightClick()){
                    if(Mana.useMana(player, 10)){
                        Location location = player.getLocation();
                        if(player.getTargetBlock(30)!=null){
                            location = player.getTargetBlock(30).getLocation();
                        }
                        player.getWorld().spawnEntity(location, EntityType.LIGHTNING);
                        _plugin.actionBar.updateActionBar();
                    }
                }
            }
            else if (player.getItemInHand().getType() == Material.FIRE_CHARGE) {//문제점 : 동물(말) 우클릭시 작동 안함
                SkillBase skill =  Skill.getSkill(player, SkillType.METEOR_STRIKE.getSkillName());
                if(skill!=null){
                    if(event.getAction().isRightClick()){
                        if(Mana.useMana(player, 10)){
                            skill.onEnable();
                            _plugin.actionBar.updateActionBar(player);
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }
}