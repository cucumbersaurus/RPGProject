package project.rpg.player.info;

import java.util.HashMap;
import java.util.Map;

public class Status {

    private String job_;
    private String playerName_;  //사람 이름

    private int additionalStatusPoint_ = 0;  //추가 스텟
    private int agility_ = 10;  //민첩
    private int attractive_ = 10;  //매력
    private int currentExp_ = 0;  //현재 경험치
    private int defense_ = 10;  //내구
    private int expNeedForNextLvl_ = 0; // 다음 레벨까지 필요경험치량 : (level
    private int handicraft_ = 10;  //손재주
    private int health_ = 10;  //체력
    private int level_ = 0;  //레벨
    private int luck_ = 10;  //행운
    private int speed_ = 10;  //신속 * 10)
    private int strength_ = 10; //힘
    private final Map<String, Integer> statusMap_ = new HashMap<>(12);

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

    public Status(String playerName) {  //생성자
        this.playerName_ = playerName;
    }

    public Status(String playerName, Map<String,Integer> hashMap) {
        this.playerName_ = playerName;
        fromMap(hashMap);
    }

    public Map<String, Integer> getMap() {
        return this.statusMap_;
    }

    public void toMap(){
        statusMap_.put(ADDITIONAL_STATUS_POINT, this.additionalStatusPoint_);
        statusMap_.put(AGILITY, this.agility_);
        statusMap_.put(ATTRACTIVE, this.attractive_);
        statusMap_.put(CURRENT_EXP, this.currentExp_);
        statusMap_.put(DEFENSE, this.defense_);
        statusMap_.put(EXP_NEED_FOR_NET_LVL, this.expNeedForNextLvl_);
        statusMap_.put(HANDICRAFT, this.handicraft_);
        statusMap_.put(HEALTH, this.health_);
        statusMap_.put(LEVEL, this.level_);
        statusMap_.put(LUCK, this.luck_);
        statusMap_.put(SPEED, this.speed_);
        statusMap_.put(STRENGTH, this.strength_);
    }

    public void fromMap(Map<String,Integer> hashMap) {
        this.additionalStatusPoint_ = hashMap.get(ADDITIONAL_STATUS_POINT);
        this.agility_ = hashMap.get(AGILITY);
        this.attractive_ = hashMap.get(ATTRACTIVE);
        this.currentExp_ = hashMap.get(CURRENT_EXP);
        this.defense_ = hashMap.get(DEFENSE);
        this.expNeedForNextLvl_ = hashMap.get(EXP_NEED_FOR_NET_LVL);
        this.handicraft_ = hashMap.get(HANDICRAFT);
        this.health_ = hashMap.get(HEALTH);
        this.level_ = hashMap.get(LEVEL);
        this.luck_ = hashMap.get(LUCK);
        this.speed_ = hashMap.get(SPEED);
        this.strength_ = hashMap.get(STRENGTH);
    }

    public String getJob() {
        return job_;
    }

    public void setJob(String job) {
        this.job_ = job;
    }

    public int getStrength() {
        return this.strength_;
    }

    public void addStrength(int strength) {
        if(this.additionalStatusPoint_ >= strength) {
            this.strength_ += strength;
            this.additionalStatusPoint_ -= strength;
            statusMap_.put(STRENGTH, this.strength_);
            statusMap_.put(ADDITIONAL_STATUS_POINT, this.additionalStatusPoint_);
        }
    }

    public int getAgility() {
        return this.agility_;
    }

    public void addAgility(int agility) {
        if(this.additionalStatusPoint_ >= agility) {
            this.agility_ += agility;
            this.additionalStatusPoint_ -= agility;
            statusMap_.put(AGILITY, this.agility_);
            statusMap_.put(ADDITIONAL_STATUS_POINT, this.additionalStatusPoint_);
        }
    }

    public int getSpeed() {
        return speed_;
    }

    public void addSpeed(int speed) {
        if(this.additionalStatusPoint_ >= speed) {
            this.speed_ += speed;
            this.additionalStatusPoint_ -= speed;
            statusMap_.put(SPEED, this.speed_);
            statusMap_.put(ADDITIONAL_STATUS_POINT, this.additionalStatusPoint_);
        }
    }

    public int getHealth() {
        return health_;
    }

    public void addHealth(int health) {
        if(this.additionalStatusPoint_ >= health) {
            this.health_ += health;
            this.additionalStatusPoint_ -= health;
            statusMap_.put(HEALTH, this.health_);
            statusMap_.put(ADDITIONAL_STATUS_POINT, this.additionalStatusPoint_);
        }
    }

    public int getDefense() {
        return defense_;
    }

    public void addDefense(int defense) {
        if(this.additionalStatusPoint_ >= defense) {
            this.defense_ += defense;
            this.additionalStatusPoint_ -= defense;
            statusMap_.put(DEFENSE, this.defense_);
            statusMap_.put(ADDITIONAL_STATUS_POINT, this.additionalStatusPoint_);
        }
    }

    public int getLuck() {
        return luck_;
    }

    public void addLuck(int luck) {
        if(this.additionalStatusPoint_ >= luck) {
            this.luck_ += luck;
            this.additionalStatusPoint_ -= luck;
            statusMap_.put(LUCK, this.luck_);
            statusMap_.put(ADDITIONAL_STATUS_POINT, this.additionalStatusPoint_);
        }
    }

    public int getHandicraft() {
        return handicraft_;
    }

    public void addHandicraft(int handicraft) {
        if(this.additionalStatusPoint_ >= handicraft) {
            this.handicraft_ += handicraft;
            this.additionalStatusPoint_ -= handicraft;
            statusMap_.put(HANDICRAFT, this.handicraft_);
            statusMap_.put(ADDITIONAL_STATUS_POINT, this.additionalStatusPoint_);
        }
    }

    public int getAttractive() {
        return attractive_;
    }

    public void addAttractive(int attractive) {
        if(this.additionalStatusPoint_ >= attractive) {
            this.attractive_ += attractive;
            this.additionalStatusPoint_ -= attractive;
            statusMap_.put(ATTRACTIVE, this.attractive_);
            statusMap_.put(ADDITIONAL_STATUS_POINT, this.additionalStatusPoint_);
        }
    }

    public int getLevel() {
        return level_;
    }

    public void setLevel(int level) {
        this.level_ = level;
        statusMap_.put(LEVEL, this.level_);
        statusMap_.put(ADDITIONAL_STATUS_POINT, this.additionalStatusPoint_);
    }

    public int getAdditionalStatusPoint() {
        return additionalStatusPoint_;
    }

    public void setAdditionalStatusPoint(int additionalStatusPoint) {
        this.additionalStatusPoint_ = additionalStatusPoint;
        statusMap_.put(ADDITIONAL_STATUS_POINT, this.additionalStatusPoint_);
    }

    public String getPlayerName() {
        return this.playerName_;
    }

    public void setPlayerName(String playerName) {
        this.playerName_ = playerName;
    }

    public boolean ifLevelUp() {
        return (this.currentExp_ >= this.expNeedForNextLvl_);
    }

    public void setLevel() {
        this.level_+=1;
        this.additionalStatusPoint_+=5;
        statusMap_.put(LEVEL, this.level_);
        statusMap_.put(ADDITIONAL_STATUS_POINT, this.additionalStatusPoint_);
    }

    public String info() {
        return  "\n================\n\n" + this.getPlayerName() + "\n\n" + this.getLevel() + ".lv\n"
                + "hp : " + this.getHealth() + "\nstrength : " + this.getStrength() + "\nagility : " + this.getAgility() + "\ndefense : " + this.getDefense()
                + "\nspeed : " + this.getSpeed() + "\nluck : " + this.getLuck() + "\nattractiveness : " + this.getAttractive() + "\nhandicraft : " + this.getHandicraft()
                + "\n\nmore : " + this.getAdditionalStatusPoint() + "\n================";
    }
}
