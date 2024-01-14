package project.rpg.items

import org.bukkit.inventory.ItemStack

object ItemManager {

    fun makeItems() {
        for (item in Items.entries) {
            item.itemBase?.createItem()
        }
    }

    @JvmStatic
    fun isEquals(item1: ItemStack, item2: ItemStack): Boolean {
        val meta1 = item1.itemMeta
        val meta2 = item2.itemMeta
        return if (meta1.hasCustomModelData() && meta2.hasCustomModelData()) {
            meta1.customModelData == meta2.customModelData
        } else false
    }
}