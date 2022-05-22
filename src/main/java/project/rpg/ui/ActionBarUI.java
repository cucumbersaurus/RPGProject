package project.rpg.ui;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import project.rpg.Rpg;

import java.util.ArrayList;
import java.util.List;

public class ActionBarUI {

    protected static final List<Player> _players = new ArrayList<>();
    private final Rpg _plugin;
    Runnable showActionBar = new Runnable() {
        @Override
        public void run() {
            for (Player p : _players) {
                String message = "체력 : "  + p.getHealth()*100 + "/" + p.getHealthScale()*100;
                message += ChatColor.BLUE + "          마나 : 100/100";
                p.sendActionBar(ChatColor.RED + message);
            }
        }
    };

    public ActionBarUI(Rpg plugin) {
        _plugin = plugin;
    }
    public void startActionBar() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(_plugin, showActionBar , 0, 40);
    }

    public void updateActionBar() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(_plugin, showActionBar, 0);
    }

    public static void addPlayer(Player player) {
        _players.add(player);
    }

    public static void deletePlayer(Player player) {
        _players.remove(player);
    }
}
