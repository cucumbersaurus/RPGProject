package project.rpg.items

import org.bukkit.inventory.ItemStack
import project.rpg.items.base.ItemBase

enum class Items(val value: Int, private val _item: ItemBase?) {
    NULL(0, null),
    WAND(1, Wand),
    MANA_REFILLING_POTION(2, ManaRefillPotion),;

    val item: ItemStack
        get() = _item!!.item
}