package project.rpg.items.weapon.magic

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.items.weapon.WeaponBase
import project.rpg.skill.magic.electricity.ThunderCharging
import project.rpg.textComponets.color.TextColors

object ThunderCharging : WeaponBase() {
    override fun createItem() {
        val item = ItemStack(Material.SPECTRAL_ARROW)
        val meta = item.itemMeta
        skill = ThunderCharging()

        meta.displayName(Component.text("전기 지지직").color(TextColors.LIGHT_YELLOW.color))
        meta.lore(itemLore())
        meta.setCustomModelData(Items.THUNDER_CHARGING.value)

        item.itemMeta = meta
        super.item = item
    }

    override fun onEnable(action: Action, player: Player) {
        skill.onEnable(player, action)
    }

    private fun itemLore():List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("천둥..?"))
        lore.add(Component.text("썬더 차징"))
        return lore
    }
}