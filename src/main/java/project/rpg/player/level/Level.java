package project.rpg.player.level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import project.rpg.player.User;

import java.util.HashMap;
import java.util.Map;

public class Level implements ConfigurationSerializable {  //레벨

    private long _level;  //레벨
    private long _exp;   //경험치
    private final Player _player;  //레벨 업 채팅 보낼 때 필요

    public long getNeedForNextLev() {  //다음까지 남은 경험치
        return 5 * (this._level * this._level + this._level);
    }

    public boolean hasEnoughExp() {  //경험치 충분한지 확인
        return this._exp >= getNeedForNextLev();
    }

    public void levelUp() {   //레벨업!
        this._exp = this._exp - getNeedForNextLev();
        User.getPlayer(_player).getStatus().addAdditionalStatusPoint(5);
        User.getPlayer(_player).getMana().reloadMaxMana();
        _level++;
        _player.sendMessage(ChatColor.YELLOW + "Level Up!");
    }

    public void addExp(long amount) {  //경험치 늘리기 나중에 몬스너나 퀘스트에서 이거 쓰면 될듯
        this._exp += amount;

        while (hasEnoughExp()) {
            levelUp();
        }
    }

    public long getLevel() {
        return _level;
    }

    public void setLevel(long level) {
        _level = level;
    }

    public long getExp() {
        return _exp;
    }

    public void setExp(long exp){
        _exp = exp;
    }

    public Level(Player player) {
        this._player = player;
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("player", _player.getName());
        map.put("level", _level);
        map.put("exp", _exp);
        return map;
    }

    public static Level deserialize(Map<String, String> map) {
        Level level = new Level(Bukkit.getPlayer(map.get("player")));
        level.setLevel(Long.parseLong(map.get("level")));
        level.setExp(Long.parseLong(map.get("exp")));
        return level;
    }

}
