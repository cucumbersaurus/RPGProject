package rpgProject.playerData;

import com.google.gson.Gson;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerStatus {

    public static HashMap<Player, PlayerStatus> map = new HashMap<>();

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
        map.put(p_, this);
    }

    public PlayerStatus(){}

    public HashMap<String, Integer> serializeStatus(){
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

    public static PlayerStatus deserializeStatus(HashMap<String, Integer> map){
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
            }
        }
        return status;
    }

    public String toJson(){
        Gson gson = new Gson();
        String statusJson = gson.toJson(this);
        return statusJson;
    }

    public static PlayerStatus fromJson(String statusJson){
        Gson gson = new Gson();
        PlayerStatus playerStatus = gson.fromJson(statusJson, PlayerStatus.class);

        return playerStatus;
    }

    public int getStrength() {
        return strength_;
    }

    public void setStrength(int strength_) {
        this.strength_ = strength_;
    }

    public int getHealth() {
        return health_;
    }

    public void setHealth(int health_) {
        this.health_ = health_;
    }

    public int getHandcraft() {
        return handcraft_;
    }

    public void setHandcraft(int handcraft_) {
        this.handcraft_ = handcraft_;
    }

    public int getAttractiveness() {
        return attractiveness_;
    }

    public void setAttractiveness(int attractiveness_) {
        this.attractiveness_ = attractiveness_;
    }

    public int getMovementSpeed() {
        return movementSpeed_;
    }

    public void setMovementSpeed(int speed_) {
        this.movementSpeed_ = speed_;
    }

    public int getAttackSpeed() {
        return attackSpeed_;
    }

    public void setAttackSpeed(int attackSpeed_) {
        this.attackSpeed_ = attackSpeed_;
    }

    public int getDefense() {
        return defense_;
    }

    public void setDefense(int defense_) {
        this.defense_ = defense_;
    }

    public int getLuck() {
        return luck_;
    }

    public void setLuck(int luck_) {
        this.luck_ = luck_;
    }
}
