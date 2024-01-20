package project.rpg.events.listeners

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import project.rpg.Rpg

class EntityTakeDamageEventListener : Listener {
    @EventHandler
    fun onEntityTakeDamage(event: EntityDamageEvent) {
        if (event.entity is Player) {
            event.damage /= 100.0
            Rpg.actionBar.updateActionBar(event.entity as Player)
        }
    }
}