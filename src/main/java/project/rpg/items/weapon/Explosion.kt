package project.rpg.items.weapon

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.entity.Fireball
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.annotation.skill
import project.rpg.effect.Stun
import project.rpg.items.Items
import project.rpg.player.User
import project.rpg.textComponets.color.TextColors

object Explosion : WeaponBase() {

    override fun createItem() {
        val item = ItemStack(Material.FIRE_CHARGE)
        val meta = item.itemMeta
        skill = project.rpg.skill.magic.fire.FlameBurst()

        meta.displayName(Component.text("☆폭발☆").color(TextColors.MAROON.color))
        meta.lore(itemLore())
        meta.setCustomModelData(Items.EXPLOSION.value)

        item.itemMeta = meta
        super.item = item
    }

    @skill(name = "flame_burst")
    override fun onEnable(action: Action, player: Player) {
        skill.onEnable(player,action)
    }

    private fun itemLore():List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("펑펑 터져라"))
        return lore
    }
}