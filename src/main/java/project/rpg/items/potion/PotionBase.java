package project.rpg.items.potion;

import org.bukkit.event.player.PlayerItemConsumeEvent;
import project.rpg.Rpg;
import project.rpg.items.ItemBase;
import project.rpg.player.mana.Mana;

public abstract class PotionBase extends ItemBase {

    public abstract void onDrink(Mana mana, Rpg rpg, PlayerItemConsumeEvent event);

}