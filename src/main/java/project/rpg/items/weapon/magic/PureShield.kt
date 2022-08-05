package project.rpg.items.weapon.magic

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.items.weapon.WeaponBase
import project.rpg.skill.magic.water.PureShield
import project.rpg.textComponets.color.TextColors

object PureShield : WeaponBase() {
    override fun createItem() {
        val item = ItemStack(Material.HEART_OF_THE_SEA)
        val meta = item.itemMeta
        skill = PureShield()

        meta.displayName(Component.text("회복").color(TextColors.AQUAMARINE.color))
        meta.lore(itemLore())
        meta.setCustomModelData(Items.PURE_SHIELD.value)

        item.itemMeta = meta
        super.item = item
    }

    override fun onEnable(action: Action, player: Player) {
        skill.onEnable(player, action)
    }

    private fun itemLore():List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("방어 & 회복"))
        lore.add(Component.text("퓨어 쉴드"))
        return lore
    }
}