package project.rpg.items.weapon.magic

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.items.weapon.WeaponBase
import project.rpg.skill.magic.earth.Binding
import project.rpg.textComponets.color.TextColors

object Binding : WeaponBase() {
    override fun createItem() {
        val item = ItemStack(Material.CHAIN)
        val meta = item.itemMeta
        skill = Binding()

        meta.displayName(Component.text("봉쇄").color(TextColors.DIM_GRAY.color))
        meta.lore(itemLore())
        meta.setCustomModelData(Items.BINDING.value)

        item.itemMeta = meta
        super.item = item
    }

    override fun onEnable(action: Action, player: Player) {
        skill.onEnable(player, action)
    }

    private fun itemLore():List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("봉쇄죠?"))
        lore.add(Component.text("바인딩"))
        return lore
    }
}