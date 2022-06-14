package project.rpg.manager

import org.bukkit.inventory.ItemStack
import project.rpg.items.ManaRefillPotion
import project.rpg.items.Wand
import project.rpg.items.base.ItemBase

object ItemManager {
    //private val itemMap: MutableMap<Items, ItemBase> = EnumMap(Items::class.java)
    private val wand: ItemBase = Wand
    private val manaRefillPotion: ItemBase = ManaRefillPotion

    fun makeItems() {
        wand.createItem()
        manaRefillPotion.createItem()

        //itemMap[Items.WAND] = wand
        //itemMap[Items.MANA_REFILLING_POTION] = manaRefillPotion
    }

    @JvmStatic
    fun isEquals(item1: ItemStack, item2: ItemStack): Boolean {
        val meta1 = item1.itemMeta
        val meta2 = item2.itemMeta
        return if (meta1.hasCustomModelData() && meta2.hasCustomModelData()) meta1.customModelData == meta2.customModelData else false
    }
}