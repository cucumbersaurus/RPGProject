package project.rpg.player.status.base;

public enum StatusName {

    AGILITY("agility"),  //민첩 공속
    DEFENSE("defense"),  //내구 방어
    HANDICRAFT("handicraft"),  //손재주
    HEALTH("health"),  //체력
    INTELLIGENCE("intelligence"),  //마력
    LUCK("luck"),  //행운
    SPEED("speed"),   //신속 달리기 속도
    STRENGTH("strength");  //힘 물리 데미지

    private final String value;

    StatusName(String v){
        this.value = v;
    }
    public String getName() {
        return value;
    }
}
