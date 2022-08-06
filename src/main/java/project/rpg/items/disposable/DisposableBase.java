package project.rpg.items.disposable;

import org.bukkit.entity.Player;
import project.rpg.items.ItemBase;

public abstract class DisposableBase extends ItemBase {

    public abstract void onUse(Player player);

}
