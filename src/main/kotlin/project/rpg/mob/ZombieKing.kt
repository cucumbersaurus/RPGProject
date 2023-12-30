package project.rpg.mob

import net.kyori.adventure.text.Component
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Mob
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

object ZombieKing : MobBase {
    override fun spawn(world: World, loc: Location): Entity {
        val entity = world.spawnEntity(loc, EntityType.ZOMBIE, false)
        with(entity as Mob) {
            customName(Component.text("좀비왕"))
            maxHealth = 200.0
            health = 200.0
            addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999, 1, true))
            equipment.setItemInMainHand(ItemStack(Material.DIAMOND_PICKAXE))
        }
        return entity
    }
}