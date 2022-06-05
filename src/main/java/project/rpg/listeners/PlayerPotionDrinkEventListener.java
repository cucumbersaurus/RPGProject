package project.rpg.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import project.rpg.Rpg;
import project.rpg.items.ManaRefillPotion;
import project.rpg.manager.ItemManager;
import project.rpg.player.info.Mana;

public class PlayerPotionDrinkEventListener implements Listener {

    private  Rpg _plugin;

    @EventHandler
    public void playerPotionDrinkEvent(PlayerItemConsumeEvent event){
         Player player = event.getPlayer();
         if(ItemManager.isEquals(event.getItem(), ManaRefillPotion.getItem())){
            Mana.addMana(player, Mana.getMaxMana(player) - Mana.getMana(player));
             _plugin._actionBar.updateActionBar(player);
        }
    }

    public PlayerPotionDrinkEventListener(Rpg plugin){
        _plugin = plugin;
    }

}