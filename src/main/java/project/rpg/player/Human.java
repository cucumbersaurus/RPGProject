package project.rpg.player;

import org.bukkit.entity.Player;
import project.rpg.player.level.Level;
import project.rpg.player.mana.Mana;
import project.rpg.player.name.Name;
import project.rpg.player.status.Stats;

import java.util.HashMap;
import java.util.UUID;

public class Human {  //사람

    public static HashMap<UUID,Human> _playerMap = new HashMap<>();  //이 해쉬맵 하나로 모든 데이터 관리!!

    private Player _player;

    private Name _name;
    private Stats _stats;
    private Mana _mana;
    private Level _level;

    public static void addPlayer(Player player) {
        _playerMap.put(player.getUniqueId(),new Human(player));
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

    public Stats getStats() {
        return _stats;
    }

    public Mana getMana() {
        return _mana;
    }

    public Level getLevel() {
        return _level;
    }

    public Human(Player player) {
        this._player = player;
        this._name = new Name(player);
        this._stats = new Stats(player);
        this._mana = new Mana();
        this._level = new Level(player);
    }
}
