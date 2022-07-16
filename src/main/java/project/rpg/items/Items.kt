package project.rpg.items

import org.bukkit.inventory.ItemStack
import project.rpg.items.base.ItemBase
import project.rpg.items.thing.ManaRefillPotion
import project.rpg.items.thing.TheMeteor
import project.rpg.items.thing.Wand

enum class Items(val value: Int, val _item: ItemBase?, val type:ItemType) {
    NULL(0, null,ItemType.NULL),
    WAND(1, Wand,ItemType.WEAPON),
    MANA_REFILLING_POTION(2, ManaRefillPotion,ItemType.POTION),
    THE_METEOR(3, TheMeteor,ItemType.WEAPON),;

    val item: ItemStack?
        get() = _item!!.item
}