package project.rpg.items.base;


import org.bukkit.inventory.ItemStack;

public abstract class ItemBase {
    protected ItemStack _item;

    public abstract void createItem();

    public ItemStack getItem() {
        return _item;
    }

}
