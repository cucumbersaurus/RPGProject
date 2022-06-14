package project.rpg.manager;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import project.rpg.items.ItemType;
import project.rpg.items.ManaRefillPotion;
import project.rpg.items.Wand;
import project.rpg.items.base.ItemBase;

import java.util.EnumMap;
import java.util.Map;

public class ItemManager {

    private static final Map<ItemType, ItemBase> _itemMap = new EnumMap<>(ItemType.class);
    private static final ItemBase _wand = Wand.INSTANCE;
    private static final ItemBase _manaRefillPotion = ManaRefillPotion.INSTANCE;

    public  ItemManager(){
    }

    public static void makeItems(){

        _wand.createItem();
        _manaRefillPotion.createItem();
        _itemMap.put(ItemType.WAND, _wand);
        _itemMap.put(ItemType.MANA_REFILLING_POTION, _manaRefillPotion);
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
