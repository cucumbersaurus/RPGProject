package project.rpg.items.objects

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.*
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import project.rpg.annotation.skill
import project.rpg.items.Items
import project.rpg.items.base.WeaponBase
import project.rpg.textComponets.color.DefaultTextColors

object TpSword: WeaponBase() {

    override fun createItem() {
        val item = ItemStack(Material.IRON_SWORD)
        val meta = item.itemMeta

        meta.displayName(Component.text("누군가의 검").color(DefaultTextColors.AQUA.color))
        meta.lore(itemLore())
        meta.setCustomModelData(Items.TP_SWORD.value)

        item.itemMeta = meta
        super.item = item
    }

    @skill(name = "shoon bow")
    override fun onEnable(player: Player?) {
        player!!.world.spawnParticle(Particle.GLOW, player.location, 25, 0.5, 1.0, 0.5, 0.7)
        var location: Location? = null
        for (all in Bukkit.getOnlinePlayers()) {
            all.playSound(player.location, Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 0.6f, 2f)
            if (all != player) {
                if (location == null || player.location.distance(all.location) < player.location
                        .distance(location)
                ) {
                    location = all.location
                }
                location.subtract(location.direction).subtract(location.direction)
                player.teleport(location)
            }
        }
    }

    private fun itemLore():List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("희한하게 생긴 검이다"))
        lore.add(Component.text("순보!!"))
        return lore
    }
}