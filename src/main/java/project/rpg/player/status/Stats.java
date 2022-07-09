package project.rpg.player.status;

import org.bukkit.entity.Player;
import project.rpg.player.status.base.StatusBase;
import project.rpg.player.status.base.StatusName;
import project.rpg.player.status.thing.*;

import java.util.HashMap;
import java.util.Map;

public class Stats {  //스텟

    private Player _player;

    private Agility _agility = new Agility();
    private Defense _defense = new Defense();
    private Handicraft _handicraft = new Handicraft();
    private Health _health = new Health();
    private Intelligence _intelligence = new Intelligence();
    private Luck _luck = new Luck();
    private Speed _speed = new Speed();
    private Strength _strength = new Strength();

    private Map<String, StatusBase> _status = new HashMap<>(8);

    private int _additionalStatusPoint;

    public void saveMap() {  //전체 저장
        _status.put(StatusName.AGILITY.getName(), _agility);
        _status.put(StatusName.DEFENSE.getName(), _defense);
        _status.put(StatusName.HANDICRAFT.getName(), _handicraft);
        _status.put(StatusName.HEALTH.getName(), _health);
        _status.put(StatusName.INTELLIGENCE.getName(), _intelligence);
        _status.put(StatusName.LUCK.getName(), _luck);
        _status.put(StatusName.SPEED.getName(), _speed);
        _status.put(StatusName.STRENGTH.getName(), _strength);
    }

    public void reloadMap() {  //적용 Attribute 매니져 없애
        for (StatusBase status : _status.values()) {
            status.effect(_player);
        }
    }

    public boolean addStatus(String name, int amount) {  //스텟 늘리기
        StatusBase status = _status.get(name);
        if (status == null) {
            throw new StringIndexOutOfBoundsException("아 제대로 하세요 스텟 이름도 모르나");
        } else {
            return status.addValue(amount,this);
        }
    }

    public void minAdditionalStatusPoint(int amount) {  //잔여 스텟 쓰기
        this._additionalStatusPoint -= amount;
    }

    public void addAdditionalStatusPoint(int amount) {  //늘리기
        this._additionalStatusPoint += amount;
    }

    public int getAdditionalStatusPoint() {  //가져오기
        return _additionalStatusPoint;
    }

    public Stats(Player player) {
        this._player = player;
    }

    {
        this._additionalStatusPoint = 10;
        this.saveMap();
    }

}
