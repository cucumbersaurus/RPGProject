package project.rpg.manager

import org.bukkit.inventory.ItemStack
import project.rpg.items.ItemType
import project.rpg.items.Items
import project.rpg.items.base.ItemBase
import project.rpg.items.objects.ManaRefillPotion
import project.rpg.items.objects.TheMeteor
import project.rpg.items.objects.Wand

object ItemManager {
    //private val itemMap: MutableMap<Items, ItemBase> = EnumMap(Items::class.java)
    private val wand: ItemBase = Wand
    private val manaRefillPotion: ItemBase = ManaRefillPotion
    private val meteor: TheMeteor = TheMeteor

    fun makeItems() {
        wand.createItem()
        manaRefillPotion.createItem()
        meteor.createItem()

        //itemMap[Items.WAND] = wand
        //itemMap[Items.MANA_REFILLING_POTION] = manaRefillPotion
    }

    @JvmStatic
    fun getItem(value: Int): ItemBase? {
        for (item in Items.values()) {
            if (item.value==value) return item._item
        }
        return null
    }

    @JvmStatic
    fun getType(value: Int): ItemType? {
        for (item in Items.values()) {
            if (item.value==value) return item.type
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