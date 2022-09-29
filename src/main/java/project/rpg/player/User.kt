package project.rpg.player;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import project.rpg.player.info.Friends;
import project.rpg.player.info.Skill;
import project.rpg.player.job.Job;
import project.rpg.player.level.Levels;
import project.rpg.player.mana.Mana;
import project.rpg.player.name.Title;
import project.rpg.player.status.Status;

import java.util.*;

public class User implements ConfigurationSerializable {  //사람

    protected static final Map<UUID, User> _playerMap = new HashMap<>();  //이 해쉬맵 하나로 모든 데이터 관리!!

    private final Player _player;

    private Title _title;
    private Status _status;
    private Mana _mana;
    private Levels _levels;
    private Job _job;

    private Skill _skill;

    private Friends _friends;//serialize 추가 필요

    public static @NotNull User newUser(Player player) {
        User user = new User(player);
        _playerMap.put(player.getUniqueId(), user);
        return user;
    }

    public static void removeUser(Player player){
        _playerMap.remove(player.getUniqueId());
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
        map.put("title", _title.serialize());
        map.put("status", _status.serialize());
        map.put("mana", _mana.serialize());
        map.put("levels", _levels.serialize());
        map.put("jobs", _job.serialize());
        map.put("skills", _skill.serialize());
        return map;
    }

    public static User deserialize(Map<String, String> map){
        Gson gson = new Gson();
        User user = new User(Bukkit.getPlayer(map.get("player")));

        user.setTitle(Title.deserialize(gson.fromJson(map.get("title"), HashMap.class)));
        user.setStatus(Status.deserialize(gson.fromJson(map.get("status"), Map.class)));
        user.setMana(Mana.deserialize(gson.fromJson(map.get("mana"), Map.class), user.getStatus()));
        user.setLevels(Levels.deserialize(gson.fromJson(map.get("levels"), Map.class)));
        user.setJob(Job.deserialize(gson.fromJson(map.get("job"), Map.class)));
        //user.setSkill(Skill.deserialize(gson.fromJson(map.get("skill"), Map.class))); //구현 미완료
        user.setSkill(new Skill(user.getPlayer()));
        return user;
    }

    public static Map<UUID, Object> serializeAll(){
        Map<UUID, Object> map = new HashMap<>();

        for(Map.Entry<UUID, User>  entry: _playerMap.entrySet()){
            map.put(entry.getKey(), entry.getValue().serialize());
        }

        return map;
    }

    public static List<User> getPlayerList(){
        return new ArrayList<>(_playerMap.values());
    }

    public Player getPlayer(){
        return _player;
    }

    public Title getTitle() {
        return _title;
    }

    public void setTitle(Title title) {
        _title = title;
    }

    public Status getStatus() {
        return _status;
    }

    public void setStatus(Status status) {
        _status = status;
    }

    public Mana getMana() {
        return _mana;
    }

    public void setMana(Mana mana) {
        _mana = mana;
    }

    public Levels getLevels() {
        return _levels;
    }

    public void setLevels(Levels levels) {
        _levels = levels;
    }

    public Job getJob() {
        return _job;
    }

    public void setJob(Job job) {
        _job = job;
    }

    public Skill getSkill() {
        return _skill;
    }

    public void setSkill(Skill skill){
        _skill = skill;
    }

    public Friends getFriends() {
        return _friends;
    }

    public void setFriends(Friends friends){
        _friends = friends;
    }

    private User(Player player) {
        this._player = player;

        this._title = new Title(player);
        this._status = new Status(player);
        this._mana = new Mana(this._status);
        this._levels = new Levels(player);
        this._job = new Job(player);
        this._skill = new Skill(player);
    }
}
