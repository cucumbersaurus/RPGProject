package project.rpg.items

import org.bukkit.inventory.ItemStack
import project.rpg.items.food.SpeedApple
import project.rpg.items.potion.ManaRefillPotion
import project.rpg.items.weapon.TpArrow
import project.rpg.items.weapon.TpSword
import project.rpg.items.weapon.Wand
import project.rpg.items.weapon.magic.*

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
    EXPLOSION(9, Explosion,ItemType.WEAPON,"explosion"),
    LAVA_ZONE(10, LavaZone,ItemType.WEAPON,"lava_zone"),
    INFERNO(11,Inferno,ItemType.WEAPON,"inferno"),
    FLARE_CLOCK(12,FlareClock,ItemType.WEAPON,"flare_clock"),
    PURE_SHIELD(13,PureShield,ItemType.WEAPON,"pure_shield"),
    WATER_ARROW(14,WaterArrow,ItemType.WEAPON,"water_arrow"),
    FROZEN_TRACE(15,FrozenTrace,ItemType.WEAPON,"frozen_trace"),
    ICE_WAVE(16,IceWave,ItemType.WEAPON,"ice_wave"),
    ICE_SPEAR(17,IceSpear,ItemType.WEAPON,"ice_spear"),
    ETERNAL_FROST(18,EternalFrost,ItemType.WEAPON,"eternal_frost"),
    LIGHTNING_STORM(19,LightningStorm,ItemType.WEAPON,"lightning_storm")
    ;

    val item: ItemStack?
        get() = itemBase!!.item
}