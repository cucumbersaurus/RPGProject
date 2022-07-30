package project.rpg.items.weapon

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.TextColor.color
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import project.rpg.annotation.skill
import project.rpg.items.Items

object TheMeteor: WeaponBase() {
    override fun createItem() {
        val item = ItemStack(Material.FIRE_CHARGE)
        val meta = item.itemMeta

        meta.displayName(text("휴대용 메테오").color(color(0xffaa00)))
        meta.lore(itemLore())
        meta.setCustomModelData(Items.THE_METEOR.value)

        item.itemMeta = meta
        super.item = item
    }

    @skill(name = "meteor")
    override fun onEnable(action: Action, player: Player?) {
        for (entity in player!!.getNearbyEntities(7.0, 7.0, 7.0)) {
            if (entity is Player && entity.gameMode==GameMode.CREATIVE) {
                continue
            }
            if (entity is LivingEntity) {
                entity.world.spawnParticle(Particle.FLAME, entity.location, 400, 0.25, 3.0, 0.25, 0.1)
                if (player != entity) {
                    if (entity.health > 10) {
                        entity.damage(10.0)
                    } else {
                        entity.damage(entity.health)
                    }
                    entity.fireTicks = 20 * 2
                    entity.addPotionEffect(PotionEffect(PotionEffectType.SLOW, 20, 2, true))
                }
            }
        }
        for (p in player.location.getNearbyPlayers(7.0, 7.0, 7.0)) {
            p.playSound(player.location, Sound.ENTITY_GENERIC_EXPLODE, 0.6f, 1f)
        }
        player.world.spawnParticle(Particle.FLAME, player.location, 100, 0.25, 3.0, 0.25, 0.1)
    }

    private fun itemLore():List<Component> {
        val lore = ArrayList<Component>()
        lore.add(text("펑펑!!"))
        return lore
    }
}