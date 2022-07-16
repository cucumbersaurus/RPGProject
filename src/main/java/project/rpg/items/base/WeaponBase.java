package project.rpg.items.base;

import org.bukkit.entity.Player;

public abstract class WeaponBase extends ItemBase {

    int _damage;
    public abstract void onEnable(Player player);

}
