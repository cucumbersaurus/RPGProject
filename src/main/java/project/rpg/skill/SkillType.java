package project.rpg.skill;

public enum SkillType {

    NULL("null"),
    METEOR_STRIKE("meteor"),
    FLAME_BURST("flame_burst"),
    BLAZING_MARK("blazing_mark"),
    EXPLOSION("explosion"),
    TP_ARROW("tp_arrow"),
    SHOONBOW("shoonbow"),
    LAVA_ZONE("lava_zone"),
    INFERNO("inferno"),
    FLARE_CLOCK("flare_clock"),
    PURE_SHIELD("pure_shield"),
    WATER_ARROW("water_arrow"),
    FROZEN_TRACE("frozen_trace"),
    ICE_WAVE("ice_wave"),
    ICE_SPEAR("ice_spear");

    private final String _skillName;

    public String getSkillName() {
        return _skillName;
    }

    SkillType(String skillName){
        _skillName = skillName;
    }

}
