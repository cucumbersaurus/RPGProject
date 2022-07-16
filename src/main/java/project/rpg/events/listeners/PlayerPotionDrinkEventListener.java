package project.rpg.events.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import project.rpg.Rpg;
import project.rpg.items.ItemType;
import project.rpg.items.Items;
import project.rpg.items.base.ItemBase;
import project.rpg.items.base.PotionBase;
import project.rpg.manager.ItemManager;
import project.rpg.player.User;
import project.rpg.player.mana.Mana;

import java.util.Objects;

public class PlayerPotionDrinkEventListener implements Listener {

    private final Rpg _plugin;

    @EventHandler
    public void playerPotionDrinkEvent(PlayerItemConsumeEvent event){
        Player player = event.getPlayer();
        Mana mana = User.getPlayer(player).getMana();

        if (event.getItem().getItemMeta().hasCustomModelData()) {
            ItemBase item = ItemManager.getItem(event.getItem().getItemMeta().getCustomModelData());

            if (item!=null && ItemManager.getType(event.getItem().getItemMeta().getCustomModelData())==ItemType.POTION) {
                if (item instanceof PotionBase) {
                    PotionBase potion = (PotionBase) item;
                    potion.onDrink(mana,_plugin,event);
                }
            }
        }
    }

    public PlayerPotionDrinkEventListener(Rpg plugin){
        _plugin = plugin;
    }

}
