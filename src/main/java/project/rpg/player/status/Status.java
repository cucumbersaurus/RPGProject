package project.rpg.player.status;

import org.bukkit.entity.Player;
import project.rpg.player.status.base.StatusBase;
import project.rpg.player.status.base.StatusName;
import project.rpg.player.status.thing.*;

import java.util.EnumMap;
import java.util.Map;

public class Status {  //스텟

    private final Player _player;

    private final Agility _agility = new Agility();
    private final Defense _defense = new Defense();
    private final Handicraft _handicraft = new Handicraft();
    private final Health _health = new Health();
    private final Intelligence _intelligence = new Intelligence();
    private final Luck _luck = new Luck();
    private final Speed _speed = new Speed();
    private final Strength _strength = new Strength();

    private final Map<StatusName, StatusBase> _status = new EnumMap<>(StatusName.class);

    private int _additionalStatusPoint;

    public void saveMap() {  //전체 저장
        _status.put(StatusName.AGILITY, _agility);
        _status.put(StatusName.DEFENSE, _defense);
        _status.put(StatusName.HANDICRAFT, _handicraft);
        _status.put(StatusName.HEALTH, _health);
        _status.put(StatusName.INTELLIGENCE, _intelligence);
        _status.put(StatusName.LUCK, _luck);
        _status.put(StatusName.SPEED, _speed);
        _status.put(StatusName.STRENGTH, _strength);
    }

    public void reloadMap() {  //적용 Attribute 매니져 없애
        for (StatusBase status : _status.values()) {
            status.effect(_player);
        }
    }

    public boolean addStatus(StatusName  name, int amount) {  //스텟 늘리기
        StatusBase status = _status.get(name);
        if (status == null) {
            throw new StringIndexOutOfBoundsException("아 제대로 하세요 스텟 이름도 모르나");
        } else {
            return status.addValue(amount,this, _player);
        }
    }

    public int getStatusValues(StatusName name) {
        StatusBase status = _status.get(name);
        if (status == null) {
            throw new StringIndexOutOfBoundsException("아 제대로 하세요 스텟 이름도 모르나");
        } else {
            return status.getValue();
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

    public Status(Player player) {
        this._player = player;
        this._additionalStatusPoint = 1000;//실제로는 10으로 할 예정, 지금은 테스트 용으로 많이 둠
        this.saveMap();
    }

}
