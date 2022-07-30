package project.rpg.items.weapon;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import project.rpg.items.ItemBase;

public abstract class WeaponBase extends ItemBase {

    int _damage;
    public abstract void onEnable(Action action, Player player);

}
