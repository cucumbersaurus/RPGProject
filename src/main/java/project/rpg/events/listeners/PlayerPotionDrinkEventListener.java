package project.rpg.events.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import project.rpg.Rpg;
import project.rpg.items.Items;
import project.rpg.manager.ItemManager;
import project.rpg.player.Human;
import project.rpg.player.mana.Mana;

import java.util.Objects;

public class PlayerPotionDrinkEventListener implements Listener {

    private final Rpg _plugin;

    @EventHandler
    public void playerPotionDrinkEvent(PlayerItemConsumeEvent event){
         Player player = event.getPlayer();
         Mana mana = Human.getPlayer(player).getMana();
         if(ItemManager.isEquals(event.getItem(), Objects.requireNonNull(Items.MANA_REFILLING_POTION.getItem()))){
             int leftUntilFull = mana.getMaxMana() - mana.getMana();
             if(leftUntilFull>=100){
                 mana.addMana(100);
             }
             else {
                 mana.addMana((mana.getMaxMana() - mana.getMana()));
             }
             _plugin.actionBar.updateActionBar(player);
        }
    }

    public PlayerPotionDrinkEventListener(Rpg plugin){
        _plugin = plugin;
    }

}
