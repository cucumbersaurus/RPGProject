package project.rpg.items.weapon.magic

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.items.weapon.WeaponBase
import project.rpg.skill.magic.fire.Inferno
import project.rpg.textComponets.color.TextColors

object Inferno : WeaponBase() {

    override fun createItem() {
        val item = ItemStack(Material.MAGMA_CREAM)
        val meta = item.itemMeta
        skill = Inferno()

        meta.displayName(Component.text("레이져 빔!").color(TextColors.INDIAN_RED.color))
        meta.lore(itemLore())
        meta.setCustomModelData(Items.INFERNO.value)

        item.itemMeta = meta
        super.item = item
    }

    override fun onEnable(action: Action, player: Player) {
        skill.onEnable(player, action)
    }

    private fun itemLore():List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("인페르노 타워"))
        lore.add(Component.text("인페르노"))
        return lore
    }

}