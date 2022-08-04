package project.rpg.skill;

public enum SkillType {

    NULL("null"),
    METEOR_STRIKE("meteor_strike"),
    FLAME_BURST("flame_burst"),
    BLAZING_MARK("blazing_mark"),
    EXPLOSION("explosion"),
    TP_ARROW("tp_arrow"),
    SHOONBOW("shoonbow"),
    LAVA_ZONE("lava_zone"),
    INFERNO("inferno"),
    FLARE_CLOCK("flare_clock"),;

    private final String _skillName;

    public String getSkillName() {
        return _skillName;
    }

    SkillType(String skillName){
        _skillName = skillName;
    }

}
