package project.rpg.ui;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import project.rpg.Rpg;
import project.rpg.manager.ArrayManager;

import java.util.ArrayList;
import java.util.List;

public class ActionBarUI {

    protected static final List<Player> _players = new ArrayList<>();
    private final Rpg _plugin;

    public ActionBarUI(Rpg plugin) {
        _plugin = plugin;
    }
    public void startActionBar() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(_plugin, new Runnable() {
            @Override
            public void run() {
                for (Player p : _players) {
                    String message = "체력 : " + ArrayManager.playerData_.get(p.getName()).getHealth();
                    message += ChatColor.BLUE + "          마나 : 100/100";
                    p.sendActionBar(ChatColor.RED + message);
                }
                //Bukkit.broadcastMessage("a");
            }
        }, 0, 40);
    }

    public static void addPlayer(Player player) {
        _players.add(player);
    }

    public static void deletePlayer(Player player) {
        _players.remove(player);
    }
}
