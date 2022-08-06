package project.rpg.events.listeners

import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.event.weather.LightningStrikeEvent
import project.rpg.effect.Burns
import project.rpg.effect.ElectricShock
import project.rpg.effect.Stun

class LightningListener : Listener {
    @EventHandler
    fun onHit(event : LightningStrikeEvent) {
        if (event.lightning.entitySpawnReason == CreatureSpawnEvent.SpawnReason.CUSTOM) {
            for (target in event.lightning.getNearbyEntities(0.5,0.5,0.5)) {
                if (target is LivingEntity) {
                    Stun(target, 2)
                    Burns(target,5)
                    ElectricShock(target,5)
                    //TODO : 방어력 4초간 50% 감소
                }
            }
        }
    }
}