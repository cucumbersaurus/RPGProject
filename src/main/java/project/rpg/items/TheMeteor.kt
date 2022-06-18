package project.rpg.items

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.TextColor.color
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import project.rpg.items.base.ItemBase

object TheMeteor: ItemBase() {
    override fun createItem() {
        val item = ItemStack(Material.FIRE_CHARGE)
        val meta = item.itemMeta

        meta.displayName(text("휴대용 메테오").color(color(0xffaa00)))
        meta.lore(itemLore())
        meta.setCustomModelData(Items.THE_METEOR.value)

        item.itemMeta = meta
        super.item = item
    }

    private fun itemLore():List<Component> {
        val lore = ArrayList<Component>()
        lore.add(text("펑펑!!"))
        return lore
    }
}