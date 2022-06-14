package project.rpg.items;

import org.bukkit.inventory.ItemStack;
import project.rpg.items.base.ItemBase;

public enum Items {

    NULL(0, null),
    WAND(1, Wand.INSTANCE),
    MANA_REFILLING_POTION(2, ManaRefillPotion.INSTANCE),;

    private final int _value;
    private final ItemBase _item;
    Items(int value, ItemBase item) {
        _value = value;
        _item = item;
    }

    public int getValue() {
        return _value;
    }

    public ItemStack getItem() {return _item.getItem();}

}
