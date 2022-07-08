package project.rpg.events.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import project.rpg.Rpg;
import project.rpg.items.Items;
import project.rpg.manager.ItemManager;
import project.rpg.player.info.Mana;

import java.util.Objects;

public class PlayerPotionDrinkEventListener implements Listener {

    private final Rpg _plugin;

    @EventHandler
    public void playerPotionDrinkEvent(PlayerItemConsumeEvent event){
         Player player = event.getPlayer();
         if(ItemManager.isEquals(event.getItem(), Objects.requireNonNull(Items.MANA_REFILLING_POTION.getItem()))){
             int leftUntilFull = Mana.getMaxMana(player) - Mana.getMana(player);
             if(leftUntilFull>=100){
                 Mana.addMana(player, 100);
             }
             else {
                 Mana.addMana(player, Mana.getMaxMana(player) - Mana.getMana(player));
             }
             _plugin.actionBar.updateActionBar(player);
        }
    }

    public PlayerPotionDrinkEventListener(Rpg plugin){
        _plugin = plugin;
    }

}
