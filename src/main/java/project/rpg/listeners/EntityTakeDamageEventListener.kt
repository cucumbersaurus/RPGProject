package project.rpg.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import project.rpg.Rpg;


public class EntityTakeDamageEventListener implements Listener {

    private final Rpg _plugin;

    public EntityTakeDamageEventListener(Rpg plugin){
        _plugin = plugin;
    }

    @EventHandler
    public void onEntityTakeDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setDamage(event.getDamage()/100.0);
            _plugin.actionBar.updateActionBar((Player) event.getEntity());
        }
    }
}
