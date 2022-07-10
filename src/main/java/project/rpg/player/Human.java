package project.rpg.player;

import org.bukkit.entity.Player;
import project.rpg.player.level.Level;
import project.rpg.player.mana.Mana;
import project.rpg.player.name.Name;
import project.rpg.player.status.Status;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Human {  //사람

    public static final Map<UUID, Human> _playerMap = new HashMap<>();  //이 해쉬맵 하나로 모든 데이터 관리!!

    private final Player _player;

    private final Name _name;
    private final Status _status;
    private final Mana _mana;
    private final Level _level;

    public static Human newHuman(Player player) {
        Human human = new Human(player);
        _playerMap.put(player.getUniqueId(), human);
        return human;
    }

    public static Human getPlayer(Player player) {
        return _playerMap.get(player.getUniqueId());
    }

    public void saveMap() {
        _playerMap.put(_player.getUniqueId(),this);
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

    private  Human(Player player) {
        this._player = player;
        this._name = new Name(player);
        this._status = new Status(player);
        this._mana = new Mana(player);
        this._level = new Level(player);
    }
}
