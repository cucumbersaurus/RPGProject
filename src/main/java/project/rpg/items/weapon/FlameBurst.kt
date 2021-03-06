package project.rpg.items.weapon

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.skill.magic.fire.FlameBurst
import project.rpg.textComponets.color.TextColors

object FlameBurst : WeaponBase() {

    override fun createItem() {
        val item = ItemStack(Material.FIRE_CHARGE)
        val meta = item.itemMeta
        skill = FlameBurst()

        meta.displayName(Component.text("화염 폭발").color(TextColors.MAROON.color))
        meta.lore(itemLore())
        meta.setCustomModelData(Items.FLAME_BURST.value)

        item.itemMeta = meta
        super.item = item
    }

    override fun onEnable(action: Action, player: Player) {
        skill.onEnable(player, action)
    }

    private fun itemLore():List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("와 배워 화염구다~"))
        return lore
    }

}