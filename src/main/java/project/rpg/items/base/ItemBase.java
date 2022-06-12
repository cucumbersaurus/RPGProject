package project.rpg.items.base;


import org.bukkit.inventory.ItemStack;

public abstract class ItemBase {
    protected ItemStack _item;

    public  void ItemBase() {
        createItem();
    }

    public  void createItem() {
    }

    public ItemStack getItem() {
        return _item;
    }

}
