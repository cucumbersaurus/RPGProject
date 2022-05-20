package project.rpg.player.info;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Status {

    protected static final Map<Player, Status> _players = new HashMap<>();

    private String _job;
    private String _playerName;  //사람 이름

    private int _additionalStatusPoint = 0;  //추가 스텟
    private int _agility = 10;  //민첩
    private int _attractive = 10;  //매력
    private int _currentExp = 0;  //현재 경험치
    private int _defense = 10;  //내구
    private int _expNeedForNextLvl = 0; // 다음 레벨까지 필요경험치량 : (level
    private int _handicraft = 10;  //손재주
    private int _health = 10;  //체력
    private int _level = 0;  //레벨
    private int _luck = 10;  //행운
    private int _speed = 10;  //신속 * 10)
    private int _strength = 10; //힘
    private final Map<String, Integer> _statusMap = new HashMap<>(12);


    private static final String ADDITIONAL_STATUS_POINT = "additionalStatusPint";
    private static final String AGILITY = "agility";
    private static final String ATTRACTIVE = "attractive";
    private static final String CURRENT_EXP = "currentExp";
    private static final String DEFENSE = "defense";
    private static final String EXP_NEED_FOR_NET_LVL = "expNeedForNextLvl";
    private static final String HANDICRAFT = "handicraft";
    private static final String HEALTH = "health";
    private static final String LEVEL = "level";
    private static final String LUCK = "luck";
    private static final String SPEED = "speed";
    private static final String STRENGTH = "strength";

    public Status(Player player) {  //생성자
        this._playerName = player.getName();
        _players.put(player, this);
    }

    public Status(String playerName, Map<String,Integer> hashMap) {
        this._playerName = playerName;
        fromMap(hashMap);
    }

    public Map<String, Integer> getMap() {
        return this._statusMap;
    }

    public void toMap(){
        _statusMap.put(ADDITIONAL_STATUS_POINT, this._additionalStatusPoint);
        _statusMap.put(AGILITY, this._agility);
        _statusMap.put(ATTRACTIVE, this._attractive);
        _statusMap.put(CURRENT_EXP, this._currentExp);
        _statusMap.put(DEFENSE, this._defense);
        _statusMap.put(EXP_NEED_FOR_NET_LVL, this._expNeedForNextLvl);
        _statusMap.put(HANDICRAFT, this._handicraft);
        _statusMap.put(HEALTH, this._health);
        _statusMap.put(LEVEL, this._level);
        _statusMap.put(LUCK, this._luck);
        _statusMap.put(SPEED, this._speed);
        _statusMap.put(STRENGTH, this._strength);
    }

    public void fromMap(Map<String,Integer> hashMap) {
        this._additionalStatusPoint = hashMap.get(ADDITIONAL_STATUS_POINT);
        this._agility = hashMap.get(AGILITY);
        this._attractive = hashMap.get(ATTRACTIVE);
        this._currentExp = hashMap.get(CURRENT_EXP);
        this._defense = hashMap.get(DEFENSE);
        this._expNeedForNextLvl = hashMap.get(EXP_NEED_FOR_NET_LVL);
        this._handicraft = hashMap.get(HANDICRAFT);
        this._health = hashMap.get(HEALTH);
        this._level = hashMap.get(LEVEL);
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

    public int getAttractive() {
        return _attractive;
    }

    public void addAttractive(int attractive) {
        if(this._additionalStatusPoint >= attractive) {
            this._attractive += attractive;
            this._additionalStatusPoint -= attractive;
            _statusMap.put(ATTRACTIVE, this._attractive);
            _statusMap.put(ADDITIONAL_STATUS_POINT, this._additionalStatusPoint);
        }
    }

    public int getLevel() {
        return _level;
    }

    public void setLevel(int level) {
        this._level = level;
        _statusMap.put(LEVEL, this._level);
        _statusMap.put(ADDITIONAL_STATUS_POINT, this._additionalStatusPoint);
    }

    public int getAdditionalStatusPoint() {
        return _additionalStatusPoint;
    }

    public void setAdditionalStatusPoint(int additionalStatusPoint) {
        this._additionalStatusPoint = additionalStatusPoint;
        _statusMap.put(ADDITIONAL_STATUS_POINT, this._additionalStatusPoint);
    }

    public String getPlayerName() {
        return this._playerName;
    }

    public void setPlayerName(String playerName) {
        this._playerName = playerName;
    }

    public boolean ifLevelUp() {
        return (this._currentExp >= this._expNeedForNextLvl);
    }

    public void setLevel() {
        this._level +=1;
        this._additionalStatusPoint +=5;
        _statusMap.put(LEVEL, this._level);
        _statusMap.put(ADDITIONAL_STATUS_POINT, this._additionalStatusPoint);
    }

    public String info() {
        return  "\n================\n\n" + this.getPlayerName() + "\n\n" + this.getLevel() + ".lv\n"
                + "hp : " + this.getHealth() + "\nstrength : " + this.getStrength() + "\nagility : " + this.getAgility() + "\ndefense : " + this.getDefense()
                + "\nspeed : " + this.getSpeed() + "\nluck : " + this.getLuck() + "\nattractiveness : " + this.getAttractive() + "\nhandicraft : " + this.getHandicraft()
                + "\n\nmore : " + this.getAdditionalStatusPoint() + "\n================";
    }
}
