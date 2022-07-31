package project.rpg.skill;

public enum SkillType {

    NULL("null"),
    METEOR_STRIKE("meteor_strike"),
    FLAME_BURST("flame_burst"),
    EXPLOSION("explosion"),
    TP_ARROW("tp_arrow"),
    SHOONBOW("shoonbow"),
    LAVA_ZONE("lava_zone");

    private final String _skillName;

    public String getSkillName() {
        return _skillName;
    }

    SkillType(String skillName){
        _skillName = skillName;
    }

}
