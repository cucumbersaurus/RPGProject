package project.rpg.player.info;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Levels {

    private static final Map<Player, Levels> _playerMap = new HashMap<>();
    private final Map<String, Long> _levels = new HashMap<>();

    private long _exp = 0;
    private int _level = 0;

    public static Map<Player, Levels> getAllMap() {
        return _playerMap;
    }

    public static Levels getLevels(Player player){
        return _playerMap.get(player);
    }

    public static void setLevel(Player player, int level) {
        Levels playerLevel = getLevels(player);
        playerLevel._level = level;
        //_players.put(player, playerLevel);
    }

    public static void setExp(Player player, long exp) {
        Levels playerLevel = getLevels(player);
        playerLevel._exp=exp;
        //_players.put(player,playerLevel);
    }

    public static void setData(Player player, int level, long exp) {
        setLevel(player, level);
        setExp(player, exp) ;
    }

    public int getLevel() {
        return _level;
    }

    public long getExp() {
        return _exp;
    }

    @Deprecated
    public static void addPlayer(Player player){
        setData(player, 0, 0);
    }

    public static long getNeedForNextLev(Player player) {
        long currentLevel = getLevels(player)._level;
        return 5 * ( currentLevel*currentLevel + currentLevel );
    }

    public static void addExp(Player player, long exp) {
        setExp(player, _playerMap.get(player)._exp+exp);

        while (hasEnoughExp(player)) {
            levelUp(player,exp);
        }

    }

    public static boolean hasEnoughExp(Player player) {
        long exp = getLevels(player)._exp;
        return exp >= getNeedForNextLev(player);
    }

    public static void levelUp(Player player,long exp) {
        setExp(player, getLevels(player)._exp - getNeedForNextLev(player));
        setLevel(player, getLevels(player)._level + 1);
        player.sendMessage(ChatColor.YELLOW + "Level Up!");
    }

    public Levels(Player player, int level, long exp) {
        _level = level;
        _exp = exp;
        _playerMap.put(player, this);
    }

}
