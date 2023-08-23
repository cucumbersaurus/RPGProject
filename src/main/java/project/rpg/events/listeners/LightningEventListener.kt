package project.rpg.events.listeners

import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.event.weather.LightningStrikeEvent
import project.rpg.effect.objects.Burns
import project.rpg.effect.objects.Damage
import project.rpg.effect.objects.ElectricShock
import project.rpg.effect.objects.Stun

class LightningEventListener : Listener {
    @EventHandler
    fun onHit(event: LightningStrikeEvent) {
        if (event.lightning.entitySpawnReason == CreatureSpawnEvent.SpawnReason.CUSTOM) {
            for (target in event.lightning.getNearbyEntities(0.5, 0.5, 0.5)) {
                if (target is LivingEntity) {
                    Stun(target, 2)
                    Burns(target, 5)
                    ElectricShock(target, 5)
                    Damage(target, 15)
                    //TODO : 방어력 4초간 50% 감소
                }
            }
        }
    }
}