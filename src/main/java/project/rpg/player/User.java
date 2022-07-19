package project.rpg.player;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import project.rpg.player.job.Job;
import project.rpg.player.level.Level;
import project.rpg.player.mana.Mana;
import project.rpg.player.name.Name;
import project.rpg.player.status.Status;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class User implements ConfigurationSerializable {  //사람

    public static final Map<UUID, User> _playerMap = new HashMap<>();  //이 해쉬맵 하나로 모든 데이터 관리!!

    private final Player _player;

    private final Name _name;
    private final Status _status;
    private final Mana _mana;
    private final Level _level;
    private final Job _job;

    public static @NotNull User newUser(Player player) {
        User user = new User(player);
        _playerMap.put(player.getUniqueId(), user);
        return user;
    }

    public static User getPlayer(@NotNull Player player) {
        return _playerMap.get(player.getUniqueId());
    }

    public void saveMap() {
        _playerMap.put(_player.getUniqueId(),this);
    }

    @Override
    public @NotNull Map<String, Object> serialize(){
        Map<String, Object> map = new HashMap<>();
        map.put("player", _player.getName());
        map.put("name", _name.serialize());
        map.put("status", _status.serialize());
        map.put("mana", _mana.serialize());
        map.put("level", _level.serialize());
        map.put("job", _job.serialize());
        return map;
    }

    public static Map<UUID, Object> serializeAll(){
        Map<UUID, Object> map = new HashMap<>();

        for(Map.Entry<UUID, User>  entry: _playerMap.entrySet()){
            map.put(entry.getKey(), entry.getValue().serialize());
        }

        return map;
    }

    public Name getName() {
        return _name;
    }

    public Status getStatus() {
        return _status;
    }

    public Mana getMana() {
        return _mana;
    }

    public Level getLevel() {
        return _level;
    }

    public Job getJob() {
        return _job;
    }

    private User(Player player) {
        this._player = player;

        this._name = new Name(player);
        this._status = new Status(player);
        this._mana = new Mana(this._status);
        this._level = new Level(player);
        this._job = new Job(player);
    }
}
