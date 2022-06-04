package project.rpg.items.base;

import org.bukkit.inventory.ItemStack;

public abstract class ItemBase {

    protected static ItemStack _item;

    public static void init() {

    }

    public static void createItem() {

    }

    public static ItemStack getItem() {
        return _item;
    }

    public static void setItem(ItemStack _item) {
        ItemBase._item = _item;
    }
}
