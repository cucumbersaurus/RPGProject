package project.rpg.skills

enum class SkillType(val skillName: String) {
    NULL("null"), METEOR_STRIKE("meteor"), FLAME_BURST("flame_burst"), BLAZING_MARK("blazing_mark"), EXPLOSION("explosion"), TP_ARROW(
        "tp_arrow"
    ),
    SHOONBOW("shoonbow"), LAVA_ZONE("lava_zone"), INFERNO("inferno"), FLARE_CLOCK("flare_clock"), PURE_SHIELD("pure_shield"), WATER_ARROW(
        "water_arrow"
    ),
    FROZEN_TRACE("frozen_trace"), ICE_WAVE("ice_wave"), ICE_SPEAR("ice_spear"), ETERNAL_FROST("eternal_frost"), LIGHTNING_STORM(
        "lightning_storm"
    ),
    THUNDER_CHARGING("thunder_charging"), LIGHTNING_CHAIN("lightning_chain"), SHOCK_WAVE("shock_wave"), GIGANTIC_THUNDER(
        "gigantic_thunder"
    ),
    HEAVEN_WING("heaven_wing"), WIND_CUTTER("wind_cutter"), TEMPEST("tempest"), BINDING("binding");

}