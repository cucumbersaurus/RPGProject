package project.rpg.player.info;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerStatus {

    public static final Map<Player, PlayerStatus> map_ = new HashMap<>();

    static Gson gson_ = new Gson();

    private Player p_;
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

    public Map<String, Object> serializeStatus(){
        HashMap<String, Object> map = new HashMap<>(8);
        map.put("player", gson_.toJson(p_.serialize()));
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

    public static PlayerStatus deserializeStatus(Map<String, Object> map){
        PlayerStatus status = new PlayerStatus();

        for(Map.Entry<String, Object> entry : map.entrySet()){
            switch (entry.getKey()){
                case "player":
                    status.setPlayer((Player) entry.getValue());
                case "strength":
                    status.setStrength((Integer) entry.getValue());
                    break;
                case "health":
                    status.setHealth((Integer) entry.getValue());
                    break;
                case "handcraft":
                    status.setHandcraft((Integer) entry.getValue());
                    break;
                case "attractiveness":
                    status.setAttractiveness((Integer) entry.getValue());
                    break;
                case "movementSpeed":
                    status.setMovementSpeed((Integer) entry.getValue());
                    break;
                case "attackSpeed":
                    status.setAttackSpeed((Integer) entry.getValue());
                    break;
                case "defense":
                    status.setDefense((Integer) entry.getValue());
                    break;
                case "luck":
                    status.setLuck((Integer) entry.getValue());
                    break;
                default:
                    break;
            }
        }
        return status;
    }

    public JsonObject toJsonObject(){
        JsonObject object = new JsonObject();

        object.addProperty("player", gson_.toJson(p_.serialize()));
        object.addProperty("strength", strength_);
        object.addProperty("health", health_);
        object.addProperty("handcraft", handcraft_);
        object.addProperty("attractiveness", attractiveness_);
        object.addProperty("movementSpeed", movementSpeed_);
        object.addProperty("attackSpeed", attackSpeed_);
        object.addProperty("defense", defense_);
        object.addProperty("luck", luck_);

        /*
        for (Map.Entry<String, Object> entry : this.serializeStatus().entrySet()){
            object.addProperty(entry.getKey(), entry.getValue().toString());
        }
        */

        return object;
    }

    private String toJson(){
        return gson_.toJson(this);
    }

    public static String toJsonFile(){
        //String jsonStr = new String();
        JsonObject array = new JsonObject();
        for (Map.Entry<Player, PlayerStatus> entry : map_.entrySet()){
            //jsonStr = jsonStr + gson_.toJson(entry.getValue().serializeStatus()) + '|';
            array.addProperty(entry.getValue().getPlayer().getName(), gson_.toJson(entry.getValue().toJsonObject()) );

        }
        return array.getAsString();
    }

    public static void fromJsonFile(String jsonString){
        ArrayList<Object> list =  gson_.fromJson(jsonString, ArrayList.class);
        Bukkit.broadcastMessage(list.toString());
    }

    public static PlayerStatus fromJson(String statusJson){
        Gson gson = new Gson();
        return  gson.fromJson(statusJson, PlayerStatus.class);
    }

    public Player getPlayer() {
        return p_;
    }

    public void setPlayer(Player p) {
        this.p_ = p;
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
