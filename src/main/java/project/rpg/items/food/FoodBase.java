package project.rpg.items.food;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import project.rpg.items.ItemBase;

public abstract class FoodBase extends ItemBase {

    public abstract void onConsume(PlayerItemConsumeEvent event);

    public void onConsume(PlayerItemConsumeEvent event,int hunger, int saturation) {
        Player player = event.getPlayer();
        player.setSaturation(player.getSaturation() + saturation);
        player.setFoodLevel(hunger);
    }

}
