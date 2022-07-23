package project.rpg.player.status;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import project.rpg.player.status.base.StatusBase;
import project.rpg.player.status.base.StatusName;
import project.rpg.player.status.thing.*;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class Status implements ConfigurationSerializable {  //스텟

    private final Player _player;

    private final Agility _agility;
    private final Defense _defense;
    private final Handicraft _handicraft;
    private final Health _health;
    private final Intelligence _intelligence;
    private final Luck _luck;
    private final Speed _speed;
    private final Strength _strength;

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

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("player", _player.getName());

        Map<String, Object> statusMap = new HashMap<>();
        for(Map.Entry<StatusName, StatusBase> entry : _status.entrySet()) {
            statusMap.put(entry.getKey().name(), entry.getValue().serialize());
        }
        map.put("status", statusMap);

        return map;
    }

    public static Status deserialize(Map<String, String> map) {
        Gson gson = new Gson();
        Player player = Bukkit.getPlayer(map.get("player"));
        Agility agility = Agility.deserialize(gson.fromJson(map.get(StatusName.AGILITY.name()), Map.class));
        Defense defense = Defense.deserialize(gson.fromJson(map.get(StatusName.DEFENSE.name()), Map.class));
        Handicraft handicraft = Handicraft.deserialize(gson.fromJson(map.get(StatusName.HANDICRAFT.name()), Map.class));
        Health health = Health.deserialize(gson.fromJson(map.get(StatusName.HEALTH.name()), Map.class));
        Intelligence intelligence = Intelligence.deserialize(gson.fromJson(map.get(StatusName.INTELLIGENCE.name()), Map.class));
        Luck luck = Luck.deserialize(gson.fromJson(map.get(StatusName.LUCK.name()), Map.class));
        Speed speed = Speed.deserialize(gson.fromJson(map.get(StatusName.SPEED.name()), Map.class));
        Strength strength = Strength.deserialize(gson.fromJson(map.get(StatusName.STRENGTH.name()), Map.class));
        return new Status(player, agility, defense, handicraft, health, intelligence, luck, speed, strength);
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
        _player = player;
        _agility = new Agility();
        _defense = new Defense();
        _handicraft = new Handicraft();
        _health = new Health();
        _intelligence = new Intelligence();
        _luck = new Luck();
        _speed = new Speed();
        _strength = new Strength();
        _additionalStatusPoint = 1000;//실제로는 10으로 할 예정, 지금은 테스트 용으로 많이 둠
        saveMap();
    }

    public Status(Player player,Agility agility, Defense defense, Handicraft handicraft, Health health, Intelligence intelligence, Luck luck, Speed speed, Strength strength) {
        _player = player;
        _agility = agility;
        _defense = defense;
        _handicraft = handicraft;
        _health = health;
        _intelligence = intelligence;
        _luck = luck;
        _speed = speed;
        _strength = strength;
    }
}
