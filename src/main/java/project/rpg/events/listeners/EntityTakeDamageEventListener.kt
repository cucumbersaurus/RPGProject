package project.rpg.events.listeners

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import project.rpg.Rpg

class EntityTakeDamageEventListener(private val _plugin: Rpg) : Listener {
    @EventHandler
    fun onEntityTakeDamage(event: EntityDamageEvent) {
        if (event.entity is Player) {
            event.damage = event.damage / 100.0
            _plugin.actionBar.updateActionBar(event.entity as Player)
            _plugin
        }
    }
}