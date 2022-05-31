package project.rpg.manager;

import org.bukkit.inventory.ItemStack;
import project.rpg.items.Wand;

public class ItemManager {

    public static void makeItems(){
        Wand.init();
    }

    public static boolean isEquals(ItemStack item1, ItemStack item2){
        return item1.getItemMeta().equals(item2.getItemMeta());
    }

}
