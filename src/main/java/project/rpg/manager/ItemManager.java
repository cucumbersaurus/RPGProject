package project.rpg.manager;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import project.rpg.items.ItemType;
import project.rpg.items.ManaRefillPotion;
import project.rpg.items.Wand;
import project.rpg.items.base.ItemBase;

import java.util.HashMap;
import java.util.Map;

public class ItemManager {

    private static final Map<ItemType, ItemBase> _itemMap = new HashMap<>();
    public  ItemManager(){

    }

    public static void makeItems(){

        _itemMap.put(ItemType.WAND, new Wand());
        _itemMap.put(ItemType.MANA_REFILLING_POTION, new ManaRefillPotion());
    }

    public static boolean isEquals(ItemStack item1, ItemStack item2){
        ItemMeta meta1 = item1.getItemMeta();
        ItemMeta meta2 = item2.getItemMeta();
        if(meta1.hasCustomModelData()&&meta2.hasCustomModelData())
            return meta1.getCustomModelData() == meta2.getCustomModelData();

        return false;
    }

    public static ItemStack getItem(ItemType itemType) {
        return _itemMap.get(itemType).getItem();
    }

}
