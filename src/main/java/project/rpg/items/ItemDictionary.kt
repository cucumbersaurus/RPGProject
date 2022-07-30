package project.rpg.items

import org.bukkit.inventory.ItemStack

object ItemDictionary {

    private val itemDictionary = HashMap<String, ItemBase?>()

    fun initialize(){
        for(i in Items.values()){
            itemDictionary[i._name] = i.itemBase
        }
    }

    fun getNewItem(itemName: String): ItemStack? {
        return itemDictionary[itemName]?.item?.clone()
    }

}