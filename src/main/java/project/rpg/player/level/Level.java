package project.rpg.player.level;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import static project.rpg.player.Human._playerMap;

public class Level {  //레벨

    private long _level;  //레벨
    private long _exp;   //경험치
    private Player _player;  //레벨 업 채팅 보낼 때 필요

    public long getNeedForNextLev() {  //다음까지 남은 경험치
        return 5 * (this._level * this._level + this._level);
    }

    public boolean hasEnoughExp() {  //경험치 충분한지 확인
        return this._exp >= getNeedForNextLev();
    }

    public void levelUp() {   //레벨업!
        this._exp = this._exp - getNeedForNextLev();
        _playerMap.get(_player.getUniqueId()).getStats().addAdditionalStatusPoint(5);
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

    public long getExp() {
        return _exp;
    }

    public Level(Player player) {
        this._player = player;
    }
}
