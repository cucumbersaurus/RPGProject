package project.rpg.items

import org.bukkit.inventory.ItemStack
import project.rpg.items.food.SpeedApple
import project.rpg.items.potion.ManaRefillPotion
import project.rpg.items.weapon.*

enum class Items(val value: Int, val itemBase: ItemBase?, val type:ItemType, val _name : String) {

    NULL(0, null, ItemType.NULL,"null"),
    WAND(1, Wand, ItemType.WEAPON,"wand"),
    MANA_REFILLING_POTION(2, ManaRefillPotion, ItemType.POTION,"mana_refilling_potion"),
    THE_METEOR(3, TheMeteor, ItemType.WEAPON,"meteor"),
    TP_SWORD(4, TpSword, ItemType.WEAPON,"tp_sword"),
    SPEED_APPLE(5, SpeedApple, ItemType.FOOD,"speed_apple"),
    BLAZING_MARK(6, BlazingMark, ItemType.WEAPON,"blazing_mark"),
    FLAME_BURST(7, FlameBurst, ItemType.WEAPON,"flame_burst"),
    TP_ARROW(8, TpArrow, ItemType.WEAPON, "tp_arrow"),
    ;

    val item: ItemStack?
        get() = itemBase!!.item
}