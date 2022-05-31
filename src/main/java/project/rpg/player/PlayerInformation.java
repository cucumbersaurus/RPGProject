package project.rpg.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import project.rpg.Rpg;
import project.rpg.manager.AttributeManager;
import project.rpg.player.info.Status;
import project.rpg.ui.ActionBarUI;

import java.util.HashMap;
import java.util.Map;

public class PlayerInformation {

    private static final Map<Player, Integer> _mana = new HashMap<>();
    private Rpg _plugin;
    public static void makeInfo(Player player){
        ActionBarUI.addPlayer(player);
        AttributeManager.setAttributes(player, new Status(player));

        player.setHealthScale(Status.getPlayerMap().get(player.getUniqueId()).getHealth()/100.0);
        player.setHealthScaled(true);
        player.setHealth(Status.getPlayerMap().get(player.getUniqueId()).getHealth()/100.0);

        _mana.put(player, 100);
    }

    public PlayerInformation(Rpg plugin){
        _plugin = plugin;
    }

    public static void setMana(Player player, int mana){
        _mana.put(player, mana);
    }

    public static int getMana(Player player){
        return _mana.getOrDefault(player, 0);
    }

    public static Map<Player, Integer> getManaMap(){
        return _mana;
    }

    public void startManaRefilling(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(_plugin, ()->{
            for(Player player : Bukkit.getOnlinePlayers()){
                if(!_mana.containsKey(player)){
                    _mana.put(player, 100);
                }
                else if(_mana.get(player)<100){
                    _mana.put(player, _mana.get(player)+1);
                }
            }
        },20, 20);
    }
}
