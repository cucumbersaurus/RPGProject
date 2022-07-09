package project.rpg.player.name.base;

import org.bukkit.entity.Player;
import project.rpg.player.name.title.MvpPP;

public enum TitleName {  //칭호 관련

    MVPPP("[MVP++]","어..? 어디서 많이 봤는데..","“2스테이지 이스터 에그” 보상"),
    HOGU("HOGU","닌 호구여~ 보상도 없는걸","인게임 5만원 이상의 무기를 무료로 판매하기");

    private String _name;  //이름
    private String _description;  //설명
    private String _acquisitionConditions;  //획득 조건

    public static TitleBase getTitle(Player player, String name) {  //ㅇㄴ 왜 String 은 ==로 안돼냐고 oracle 만들어줘 ㄴ야ㅗㄹ넝류미ㅑㄴㄹㅇ미ㅑㅇㄹ
        if (name.equals(MVPPP._name)) {
            return new MvpPP(player);
        }
        return null;
    }

    public String getName() {
        return _name;
    }

    public String getDescription() {
        return _description;
    }

    public String getAcquisitionConditions() {
        return _acquisitionConditions;
    }

    TitleName(String v){
        this(v,null,null);
    }

    TitleName(String name, String description, String acquisitionConditions) {
        this._name = name;
        this._description = description;
        this._acquisitionConditions = acquisitionConditions;
    }

}
