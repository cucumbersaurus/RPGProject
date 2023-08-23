package project.rpg.items

import org.bukkit.inventory.ItemStack

object ItemManager {

    fun makeItems() {
        for (item in Items.values()) {
            item.itemBase?.createItem()
        }
        //itemMap[Items.WAND] = wand
        //itemMap[Items.MANA_REFILLING_POTION] = manaRefillPotion
    }

    @JvmStatic
    @Deprecated("시간복잡도가 너무 큼")
    fun getItem(value: Int): ItemBase? {
        for (item in Items.values()) {
            if (item.value == value) return item.itemBase
        }
        return null
    }

    @JvmStatic
    @Deprecated("시간복잡도가 너무 큼")
    fun getType(value: Int): ItemType? {
        for (item in Items.values()) {
            if (item.value == value) return item.type
        }
        return null
    }

    @JvmStatic
    @Deprecated("시간복잡도가 너무 큼")
    fun getName(value: Int): String? {
        for (item in Items.values()) {
            if (item.value == value) {
                return item.displayName
            }
        }
        return null
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