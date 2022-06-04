package project.rpg.manager;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import project.rpg.items.ManaRefillPotion;
import project.rpg.items.Wand;

public class ItemManager {

    public  ItemManager(){

    }

    public static void makeItems(){
        Wand.init();
        ManaRefillPotion.init();
    }

    public static boolean isEquals(ItemStack item1, ItemStack item2){
        ItemMeta meta1 = item1.getItemMeta();
        ItemMeta meta2 = item2.getItemMeta();
        if(meta1.hasCustomModelData()&&meta2.hasCustomModelData())
            return meta1.getCustomModelData() == meta2.getCustomModelData();

        return false;
    }

}
