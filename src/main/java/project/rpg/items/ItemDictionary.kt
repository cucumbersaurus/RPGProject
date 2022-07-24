package project.rpg.items

import org.bukkit.inventory.ItemStack
import project.rpg.items.base.ItemBase

object ItemDictionary {

    private val itemDictionary = HashMap<String, ItemBase?>()

    fun initialize(){
        for(i in Items.values()){
            itemDictionary[i.name] = i._item;
        }
    }

    fun getNewItem(itemName: String): ItemStack? {
        return itemDictionary[itemName]?.item?.clone()
    }



}