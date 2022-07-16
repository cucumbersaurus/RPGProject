package project.rpg.items

import org.bukkit.inventory.ItemStack
import project.rpg.items.base.ItemBase

enum class Items(val value: Int, val _item: ItemBase?, val type:ItemType) {
    NULL(0, null,ItemType.NULL),
    WAND(1, Wand,ItemType.WEAPON),
    MANA_REFILLING_POTION(2, ManaRefillPotion,ItemType.POTION),
    THE_METEOR(3, TheMeteor,ItemType.WEAPON),;

    val item: ItemStack?
        get() = _item!!.item
}

enum class ItemType(val value: Int) {

    NULL(0),
    WEAPON(1),
    POTION(2),
    TOOL(3),
    MATERIAL(4),
    FOOD(5),
    TROPHY(6),
    JAPTEM(7);

}