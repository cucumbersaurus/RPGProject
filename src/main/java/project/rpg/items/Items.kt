package project.rpg.items

import org.bukkit.inventory.ItemStack
import project.rpg.items.disposable.ManaStone
import project.rpg.items.food.HighEnrichmentCandy
import project.rpg.items.food.SpeedApple
import project.rpg.items.potion.HealingPotion
import project.rpg.items.potion.ManaRefillPotion
import project.rpg.items.potion.TearOfDragon
import project.rpg.items.weapon.TpArrow
import project.rpg.items.weapon.TpSword
import project.rpg.items.weapon.Wand
import project.rpg.items.weapon.magic.*

enum class Items(val value: Int, val itemBase: ItemBase?, val type: ItemType, val displayName: String) {

    NULL(0, null, ItemType.NULL, "null"),
    WAND(1, Wand, ItemType.SKILL, "wand"),
    MANA_REFILLING_POTION(2, ManaRefillPotion, ItemType.POTION, "mana_refilling_potion"),
    THE_METEOR(3, TheMeteor, ItemType.SKILL, "meteor"),
    TP_SWORD(4, TpSword, ItemType.SKILL, "tp_sword"),
    SPEED_APPLE(5, SpeedApple, ItemType.FOOD, "speed_apple"),
    BLAZING_MARK(6, BlazingMark, ItemType.SKILL, "blazing_mark"),
    FLAME_BURST(7, FlameBurst, ItemType.SKILL, "flame_burst"),
    TP_ARROW(8, TpArrow, ItemType.SKILL, "tp_arrow"),
    EXPLOSION(9, Explosion, ItemType.SKILL, "explosion"),
    LAVA_ZONE(10, LavaZone, ItemType.SKILL, "lava_zone"),
    INFERNO(11, Inferno, ItemType.SKILL, "inferno"),
    FLARE_CLOCK(12, FlareClock, ItemType.SKILL, "flare_clock"),
    PURE_SHIELD(13, PureShield, ItemType.SKILL, "pure_shield"),
    WATER_ARROW(14, WaterArrow, ItemType.SKILL, "water_arrow"),
    FROZEN_TRACE(15, FrozenTrace, ItemType.SKILL, "frozen_trace"),
    ICE_WAVE(16, IceWave, ItemType.SKILL, "ice_wave"),
    ICE_SPEAR(17, IceSpear, ItemType.SKILL, "ice_spear"),
    ETERNAL_FROST(18, EternalFrost, ItemType.SKILL, "eternal_frost"),
    LIGHTNING_STORM(19, LightningStorm, ItemType.SKILL, "lightning_storm"),
    THUNDER_CHARGING(20, ThunderCharging, ItemType.SKILL, "thunder_charging"),
    LIGHTNING_CHAIN(21, LightningChain, ItemType.SKILL, "lightning_chain"),
    SHOCK_WAVE(22, ShockWave, ItemType.SKILL, "shock_wave"),
    GIGANTIC_THUNDER(23, GiganticThunder, ItemType.SKILL, "gigantic_thunder"),
    HIGH_ENRICHMENT_CANDY(24, HighEnrichmentCandy, ItemType.FOOD, "high_enrichment_candy"),
    MANA_STONE(25, ManaStone, ItemType.DISPOSABLE, "mana_stone"),
    TEAR_OF_DRAGON(26, TearOfDragon, ItemType.POTION, "tear_of_dragon"),
    HEALING_POTION(27, HealingPotion, ItemType.POTION, "healing_potion"),
    HEAVEN_WING(28, HeavenWing, ItemType.SKILL, "heaven_wing"),
    WIND_CUTTER(29, WindCutter, ItemType.SKILL, "wind_cutter"),
    TEMPEST(30, Tempest, ItemType.SKILL, "tempest"),
    BINDING(31, Binding, ItemType.SKILL, "binding"),
    ;

    val item: ItemStack?
        get() = itemBase?.item
}