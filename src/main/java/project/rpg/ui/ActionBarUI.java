package project.rpg.ui;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import project.rpg.Rpg;
import project.rpg.player.info.Mana;

import java.util.ArrayList;
import java.util.List;

public class ActionBarUI {

    protected static final List<Player> _players = new ArrayList<>();
    private final Rpg _plugin;
    Runnable showActionBar = () -> {
        for (Player player : _players) {
            String message = "체력 : "  + String.format("%.2f", player.getHealth()*100) + "/" + String.format("%.2f", player.getHealthScale()*100);
            message += ChatColor.BLUE + "          마나 : " + Mana.getMana(player) + "/" + Mana.getMaxMana(player);
            player.sendActionBar(ChatColor.RED + message);
        }
    };

    public ActionBarUI(Rpg plugin) {
        _plugin = plugin;
    }
    public void startActionBar() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(_plugin, showActionBar , 0, 10);
    }

    public void updateActionBar() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(_plugin, showActionBar, 0);
    }

    public void updateActionBar(Player player) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(_plugin, ()->{
            String message = "체력 : "  + String.format("%.2f", player.getHealth()*100) + "/" + String.format("%.2f", player.getHealthScale()*100);
            message += ChatColor.BLUE + "          마나 : "  + Mana.getMana(player) + "/" + Mana.getMaxMana(player);
            player.sendActionBar(ChatColor.RED + message);
        }, 0);
    }

    public static void addPlayer(Player player) {
        _players.add(player);
    }

    public static void deletePlayer(Player player) {
        _players.remove(player);
    }

}
