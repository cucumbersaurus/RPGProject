package project.rpg.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import project.rpg.Rpg;
import project.rpg.manager.AttributeManager;
import project.rpg.player.info.Skill;
import project.rpg.player.info.Status;
import project.rpg.ui.ActionBarUI;

import java.util.HashMap;
import java.util.Map;

import static project.rpg.player.info.Skill._skills;

public class PlayerInformation {

    private static final Map<Player, Integer> _mana = new HashMap<>();
    private final Rpg _plugin;
    public static void makeInfo(Player player){
        ActionBarUI.addPlayer(player);
        AttributeManager.setAttributes(player, new Status(player));

        player.setHealthScale(Status.getPlayerMap().get(player.getUniqueId()).getHealth()/100.0);
        player.setHealthScaled(true);
        player.setHealth(Status.getPlayerMap().get(player.getUniqueId()).getHealth()/100.0);

        _skills.put(player.getName(), new Skill());
    }

    public PlayerInformation(Rpg plugin){
        _plugin = plugin;
    }

    public static void useMana(Player player, int mana){
        _skills.get(player.getName()).useMana(mana);
    }

    public static int getMana(Player player){
        if (_skills.get(player.getName())!=null) {
            return _skills.get(player.getName()).getMana();
        } else {
            return 0;
        }
    }

    public static int getMaxMana(Player player) {
        if (_skills.get(player.getName())!=null) {
            return _skills.get(player.getName()).getMaxMana();
        } else {
            return 0;
        }
    }

    public static Map<String, Skill> getManaMap(){
        return _skills;
    }

    public void startManaRefilling(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(_plugin, ()->{
            for(Player player : Bukkit.getOnlinePlayers()){
                if(!_skills.containsKey(player.getName())){
                    _skills.put(player.getName(), new Skill(100));
                }
                else if(_skills.get(player.getName()).getMana()< _skills.get(player.getName()).getMaxMana()){
                    _skills.get(player.getName()).plusMana();
                }
            }
        },10, 10); //마나 회복이 지나치게 느림
    }
}
