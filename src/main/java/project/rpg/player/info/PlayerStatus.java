package project.rpg.player.info;

import com.google.gson.Gson;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerStatus {

    public static final Map<Player, PlayerStatus> map_ = new HashMap<>();

    Player p_;
    private int strength_ = 0;
    private int health_ = 0;
    private int handcraft_ = 0;
    private int attractiveness_ = 0;
    private int movementSpeed_ = 0;
    private int attackSpeed_ = 0;
    private int defense_ = 0;
    private int luck_ = 0;

    public PlayerStatus(Player player){
        p_ = player;
        map_.put(p_, this);
    }

    public PlayerStatus(){}

    public Map<String, Integer> serializeStatus(){
        HashMap<String, Integer> map = new HashMap<>(8);
        map.put("strength", strength_);
        map.put("health", health_);
        map.put("handcraft", handcraft_);
        map.put("attractiveness", attractiveness_);
        map.put("movementSpeed", movementSpeed_);
        map.put("attackSpeed", attackSpeed_);
        map.put("defense", defense_);
        map.put("luck", luck_);

        return map;
    }

    public static PlayerStatus deserializeStatus(Map<String, Integer> map){
        PlayerStatus status = new PlayerStatus();

        for(Map.Entry<String, Integer> entry : map.entrySet()){
            switch (entry.getKey()){
                case "strength":
                    status.setStrength(entry.getValue());
                    break;
                case "health":
                    status.setHealth(entry.getValue());
                    break;
                case "handcraft":
                    status.setHandcraft(entry.getValue());
                    break;
                case "attractiveness":
                    status.setAttractiveness(entry.getValue());
                    break;
                case "movementSpeed":
                    status.setMovementSpeed(entry.getValue());
                    break;
                case "attackSpeed":
                    status.setAttackSpeed(entry.getValue());
                    break;
                case "defense":
                    status.setDefense(entry.getValue());
                    break;
                case "luck":
                    status.setLuck(entry.getValue());
                    break;
                default:
                    break;
            }
        }
        return status;
    }

    private String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static String toJsonFile(){
        String jsonStr = new String();
        for (Map.Entry<Player, PlayerStatus> entry : map_.entrySet()){
            jsonStr = jsonStr + entry.getValue().toJson();
        }
        return jsonStr;
    }

    public static PlayerStatus fromJson(String statusJson){
        Gson gson = new Gson();
        return  gson.fromJson(statusJson, PlayerStatus.class);
    }

    public int getStrength() {
        return strength_;
    }

    public void setStrength(int strength) {
        this.strength_ = strength;
    }

    public int getHealth() {
        return health_;
    }

    public void setHealth(int health) {
        this.health_ = health;
    }

    public int getHandcraft() {
        return handcraft_;
    }

    public void setHandcraft(int handcraft) {
        this.handcraft_ = handcraft;
    }

    public int getAttractiveness() {
        return attractiveness_;
    }

    public void setAttractiveness(int attractiveness) {
        this.attractiveness_ = attractiveness;
    }

    public int getMovementSpeed() {
        return movementSpeed_;
    }

    public void setMovementSpeed(int speed) {
        this.movementSpeed_ = speed;
    }

    public int getAttackSpeed() {
        return attackSpeed_;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed_ = attackSpeed;
    }

    public int getDefense() {
        return defense_;

    }

    public void setDefense(int defense) {
        this.defense_ = defense;
    }

    public int getLuck() {
        return luck_;
    }

    public void setLuck(int luck) {
        this.luck_ = luck;
    }
}
