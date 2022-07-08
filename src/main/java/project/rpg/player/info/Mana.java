package project.rpg.player.info;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import project.rpg.Rpg;

import java.util.HashMap;
import java.util.Map;


public class Mana {

    private final Rpg _plugin;

    private static final Map<Player, Integer> _mana = new HashMap<>();
    private static final Map<Player, Integer> _maxMana = new HashMap<>();


    public Mana(Rpg plugin){
        _plugin = plugin;
    }

    public static Map<Player, Integer> getManaMap(){
        return _mana;
    }

    public static Map<Player, Integer> getMaxManaMap(){
        return _maxMana;
    }

    public static void addPlayer(Player player){
        int maxMana = Status.getPlayerMap().get(player.getUniqueId()).getIntelligence()*10;
        _mana.put(player, maxMana);
        _maxMana.put(player, maxMana);
    }

    public static boolean useMana(Player player, int mana){
        if(mana<=_mana.get(player)){
            _mana.put(player, _mana.get(player) - mana);
            return true;
        }
        return false;
    }

    public static int getMana(Player player){
        return _mana.get(player);
    }

    public static int getMaxMana(Player player) {
        return _maxMana.get(player);
    }

    public static boolean addMana(Player player, int mana){
        if(mana+_mana.get(player)<=_maxMana.get(player)){
            _mana.put(player, _mana.get(player) + mana);
            return true;
        }
        return false;
    }

    public static void reloadMaxMana(Player player){
        _maxMana.put(player, Status.getPlayerMap().get(player.getUniqueId()).getIntelligence()*10);
    }

    public void startManaRefilling(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(_plugin, ()->{
            for(Player player : _mana.keySet()){
                if(getMana(player)<getMaxMana(player))  addMana(player, 1);
            }
        }, 10, 10); //마나 회복이 지나치게 느림
    }
}
