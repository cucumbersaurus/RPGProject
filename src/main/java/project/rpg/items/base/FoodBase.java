package project.rpg.items.base;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public abstract class FoodBase extends ItemBase {

    public abstract void onConsume(PlayerItemConsumeEvent event);

    public void onConsume(PlayerItemConsumeEvent event,int hunger, int saturation) {
        Player player = event.getPlayer();
        player.setSaturation(player.getSaturation() + saturation);
        player.setFoodLevel(hunger);
    }

}
