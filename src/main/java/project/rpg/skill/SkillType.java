package project.rpg.skill;

public enum SkillType {

    NULL("null"),
    METEOR_STRIKE("meteor_strike");

    private final String _skillName;

    public String getSkillName() {
        return _skillName;
    }

    SkillType(String skillName){
        _skillName = skillName;
    }

}
