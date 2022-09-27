package project.rpg.mob

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.Mob
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class ZombieKing: MobBase {

    override fun createMobFun(): (entity: Entity) -> Unit {
        return { entity: Entity ->
            if(entity is Mob) {
                with(entity) {
                    customName(Component.text("좀비왕"))
                    maxHealth = 200.0
                    health = 200.0
                    addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999, 1, true))
                    equipment.setItemInMainHand(ItemStack(Material.DIAMOND_PICKAXE))
                }
            }
        }
    }
}