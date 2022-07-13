package project.rpg.player.name.base;

import org.bukkit.entity.Player;

public abstract class TitleBase {  //칭호 베이스

    protected final Player _player;  //플레이어 onEnable 이나 disable 에서 쓸때
    protected final String _name;   //칭호 이릉
    protected final String _desciption;  //칭호 설명
    protected final String _acquisitionConditions;  //획득 조건

    public abstract void onEnable();  //착용시 효과    -> 교감 신경
    public abstract void onDisable();  //착용 해제시   -> 부교감 신경
    public abstract void eared();   //획득시 발동

    public String getName() {
        return _name;
    }

    protected TitleBase(Player player,String name) {
        this(player,name,null,null);
    }

    protected TitleBase(Player player,String name,String desciption,String condition) {
        this._player = player;
        this._name = name;
        this._desciption = desciption;
        this._acquisitionConditions = condition;
    }
}
