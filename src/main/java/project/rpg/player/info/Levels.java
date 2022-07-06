package project.rpg.player.info;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Levels {

    private static final Map<Player, Levels> _players = new HashMap<>();
    private final Map<String, Long> _levels = new HashMap<>();

    private long _exp = 0;
    private int _level = 0;

    public static Map<Player, Levels> getAllMap() {
        return _players;
    }

    public static Levels getLevels(Player player){
        return _players.get(player);
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

    public static void setLevels(Player player, int level, long exp) {
        setLevel(player, level);
        setExp(player, exp) ;
    }

    public int getLevel() {
        return _level;
    }

    public long getExp() {
        return _exp;
    }

    public static void addPlayer(Player player){
        setLevels(player, 0, 0);
    }

    public static long getNeedForNextLev(Player player) {
        long currentLevel = getLevels(player)._level;
        return 5 * ( currentLevel*currentLevel + currentLevel );
    }

    public static void addExp(Player player, long exp) {
        setExp(player, _players.get(player)._exp+exp);

        while (hasEnoughExp(player)) {
            levelUp(player,exp);
        }

    }

    public static boolean hasEnoughExp(Player player) {
        long exp = getLevels(player)._exp;
        return exp >= getNeedForNextLev(player);
    }

    public static void levelUp(Player player,long exp) {
        setLevel(player, getLevels(player)._level + 1);
        setExp(player, getLevels(player)._exp - getNeedForNextLev(player));
        player.sendMessage(ChatColor.YELLOW + "Level Up!");
    }

}
