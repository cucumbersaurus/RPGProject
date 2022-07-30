package project.rpg.items.objects.weapon

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.entity.Arrow
import org.bukkit.entity.Fireball
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import project.rpg.annotation.skill
import project.rpg.items.Items
import project.rpg.items.base.WeaponBase
import project.rpg.player.User
import project.rpg.textComponets.color.DefaultTextColors

object FlameBurst : WeaponBase() {

    override fun createItem() {
        val item = ItemStack(Material.FIRE_CHARGE)
        val meta = item.itemMeta

        meta.displayName(Component.text("화염 폭발").color(DefaultTextColors.MAROON.color))
        meta.lore(itemLore())
        meta.setCustomModelData(Items.FLAME_BURST.value)

        item.itemMeta = meta
        super.item = item
    }

    @skill(name = "flame_burst")
    override fun onEnable(player: Player) {
        val mana = User.getPlayer(player).mana

        if (mana.useMana(10)) {

            val fireball: Fireball = player.launchProjectile<Fireball>(Fireball::class.java)
            fireball.velocity = player.location.direction.multiply(5)
        }
        player.world.spawnParticle(Particle.FLAME, player.location, 100, 0.25, 3.0, 0.25, 0.1)
    }

    fun itemLore():List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("와 배워 화염구다~"))
        return lore
    }

}