package project.rpg.player.info;

import org.bukkit.entity.Player;
import project.rpg.player.PlayerInformation;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Status {

    protected static Map<UUID, Status> _players = new HashMap<>();

    private String _job;
    private final Player _player;  //사람 이름

    private int _additionalStatusPoint = 1200;  //추가 스텟
    private int _agility = 10;  //민첩
    private int _intelligence = 10;  //마력
    private int _defense = 10;  //내구
    private int _handicraft = 10;  //손재주
    private int _health = 100;  //체력
    private int _luck = 10;  //행운
    private int _speed = 10;  //신속 * 10)
    private int _strength = 10; //힘
    private final Map<String, Integer> _statusMap = new HashMap<>(12);


    private static final String ADDITIONAL_STATUS_POINT = "additionalStatusPint";
    private static final String AGILITY = "agility";
    private static final String INTELLIGENCE = "intelligence";
    private static final String DEFENSE = "defense";
    private static final String HANDICRAFT = "handicraft";
    private static final String HEALTH = "health";
    private static final String LUCK = "luck";
    private static final String SPEED = "speed";
    private static final String STRENGTH = "strength";

    public Status(Player player) {  //생성자
        this._player = player;
        _players.put(player.getUniqueId(), this);
    }

    public Status(Player player, Map<String,Integer> hashMap) {
        this._player = player;
        fromMap(hashMap);
    }

    public Map<String, Integer> getMap() {
        return _statusMap;
    }

    public static Map<UUID, Status> getPlayerMap() {
        return _players;
    }

    public static Status getPlayer(Player player) {
        return _players.get(player.getUniqueId());
    }

    public static void setPlayerMap(Map<UUID, Status> map){
        _players = map;
    }

    public void toMap(){
        _statusMap.put(ADDITIONAL_STATUS_POINT, this._additionalStatusPoint);
        _statusMap.put(AGILITY, this._agility);
        _statusMap.put(INTELLIGENCE, this._intelligence);
        _statusMap.put(DEFENSE, this._defense);
        _statusMap.put(HANDICRAFT, this._handicraft);
        _statusMap.put(HEALTH, this._health);
        _statusMap.put(LUCK, this._luck);
        _statusMap.put(SPEED, this._speed);
        _statusMap.put(STRENGTH, this._strength);
    }

    public void fromMap(Map<String,Integer> hashMap) {
        this._additionalStatusPoint = hashMap.get(ADDITIONAL_STATUS_POINT);
        this._agility = hashMap.get(AGILITY);
        this._intelligence = hashMap.get(INTELLIGENCE);
        this._defense = hashMap.get(DEFENSE);
        this._handicraft = hashMap.get(HANDICRAFT);
        this._health = hashMap.get(HEALTH);
        this._luck = hashMap.get(LUCK);
        this._speed = hashMap.get(SPEED);
        this._strength = hashMap.get(STRENGTH);
    }

    public String getJob() {
        return _job;
    }

    public void setJob(String job) {
        this._job = job;
    }

    public int getStrength() {
        return this._strength;
    }

    public void addStrength(int strength) {
        if(this._additionalStatusPoint >= strength) {
            this._strength += strength;
            this._additionalStatusPoint -= strength;
            _statusMap.put(STRENGTH, this._strength);
            _statusMap.put(ADDITIONAL_STATUS_POINT, this._additionalStatusPoint);
        }
    }

    public int getAgility() {
        return this._agility;
    }

    public void addAgility(int agility) {
        if(this._additionalStatusPoint >= agility) {
            this._agility += agility;
            this._additionalStatusPoint -= agility;
            _statusMap.put(AGILITY, this._agility);
            _statusMap.put(ADDITIONAL_STATUS_POINT, this._additionalStatusPoint);
        }
    }

    public int getSpeed() {
        return _speed;
    }

    public void addSpeed(int speed) {
        if(this._additionalStatusPoint >= speed) {
            this._speed += speed;
            this._additionalStatusPoint -= speed;
            _statusMap.put(SPEED, this._speed);
            _statusMap.put(ADDITIONAL_STATUS_POINT, this._additionalStatusPoint);
        }
    }

    public int getHealth() {
        return _health;
    }

    public void addHealth(int health) {
        if(this._additionalStatusPoint >= health) {
            this._health += health;
            this._additionalStatusPoint -= health;
            _statusMap.put(HEALTH, this._health);
            _statusMap.put(ADDITIONAL_STATUS_POINT, this._additionalStatusPoint);
            PlayerInformation.updateHealth(_player);
        }
    }

    public int getDefense() {
        return _defense;
    }

    public void addDefense(int defense) {
        if(this._additionalStatusPoint >= defense) {
            this._defense += defense;
            this._additionalStatusPoint -= defense;
            _statusMap.put(DEFENSE, this._defense);
            _statusMap.put(ADDITIONAL_STATUS_POINT, this._additionalStatusPoint);
        }
    }

    public int getLuck() {
        return _luck;
    }

    public void addLuck(int luck) {
        if(this._additionalStatusPoint >= luck) {
            this._luck += luck;
            this._additionalStatusPoint -= luck;
            _statusMap.put(LUCK, this._luck);
            _statusMap.put(ADDITIONAL_STATUS_POINT, this._additionalStatusPoint);
        }
    }

    public int getHandicraft() {
        return _handicraft;
    }

    public void addHandicraft(int handicraft) {
        if(this._additionalStatusPoint >= handicraft) {
            this._handicraft += handicraft;
            this._additionalStatusPoint -= handicraft;
            _statusMap.put(HANDICRAFT, this._handicraft);
            _statusMap.put(ADDITIONAL_STATUS_POINT, this._additionalStatusPoint);
        }
    }

    public int getIntelligence() {
        return _intelligence;
    }

    public void addIntelligence(int intelligence) {
        if(this._additionalStatusPoint >= intelligence) {
            this._intelligence += intelligence;
            this._additionalStatusPoint -= intelligence;
            _statusMap.put(INTELLIGENCE, this._intelligence);
            _statusMap.put(ADDITIONAL_STATUS_POINT, this._additionalStatusPoint);
        }
    }

    public int getAdditionalStatusPoint() {
        return _additionalStatusPoint;
    }

    public void setAdditionalStatusPoint(int additionalStatusPoint) {
        this._additionalStatusPoint = additionalStatusPoint;
        _statusMap.put(ADDITIONAL_STATUS_POINT, this._additionalStatusPoint);
    }

    public String getPlayerName() {
        return this._player.getName();
    }

}