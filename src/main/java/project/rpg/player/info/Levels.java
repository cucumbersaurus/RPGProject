package project.rpg.player.info;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Levels {

    private static final Map<Player, Map<String, Long>> _players = new HashMap<>();
    private final Map<String, Long> _levels = new HashMap<>();

    public static final String EXP = "exp";
    public static final String LEVEL = "level";

    public static Map<Player, Map<String, Long>> getAllMap() {
        return _players;
    }

    public static Map<String, Long> getPlayerMap(Player player){
        return _players.get(player);
    }

    public static void savePlayerMap(Player player, int level) {
        getPlayerMap(player).put(LEVEL, (long) level);
        _players.put(player,getPlayerMap(player));
    }

    public static void savePlayerMap(Player player, long exp) {
        getPlayerMap(player).put(EXP,exp);
        _players.put(player,getPlayerMap(player));
    }

    public static void savePlayerMap(Player player, int level, long exp) {
        getPlayerMap(player).put(EXP,exp);
        getPlayerMap(player).put(LEVEL, (long) level);
        _players.put(player,getPlayerMap(player));
    }

    public static void addPlayer(Player player){
        savePlayerMap(player,0,0);
    }

    public static long getNeedForNextLev(Player player) {
        long currentLevel = getPlayerMap(player).get(LEVEL);
        return 5 * ( currentLevel*currentLevel + currentLevel );
    }

    public static void addExp(Player player, long exp) {
        savePlayerMap(player, _players.get(player).get(EXP)+exp);

        while (ifLevelUp(player)) {
            levelUp(player,exp);
        }

    }

    public static boolean ifLevelUp(Player player) {
        long exp = getPlayerMap(player).get(EXP);
        if (exp >= getNeedForNextLev(player)) {
            return true;
        }
        return false;
    }

    public static void levelUp(Player player,long exp) {
        savePlayerMap(player,getPlayerMap(player).get(LEVEL).intValue()+1);
        savePlayerMap(player,getPlayerMap(player).get(EXP)-getNeedForNextLev(player));
        player.sendMessage(ChatColor.YELLOW + "Level Up!");
    }

}
