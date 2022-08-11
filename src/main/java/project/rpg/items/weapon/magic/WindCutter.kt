package project.rpg.items.weapon.magic

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.items.weapon.MagicItemBase
import project.rpg.skill.magic.wind.WindCutter
import project.rpg.textComponets.color.TextColors

object WindCutter : MagicItemBase() {
    override fun createItem() {
        val item = ItemStack(Material.BAMBOO)
        val meta = item.itemMeta
        skill = WindCutter()

        meta.displayName(Component.text("바람 자르기").color(TextColors.LIGHT_BLUE.color))
        meta.lore(itemLore())
        meta.setCustomModelData(Items.WIND_CUTTER.value)

        item.itemMeta = meta
        super.item = item
    }

    override fun onEnable(player: Player, action: Action) {
        skill.onEnable(player, action)
    }

    private fun itemLore():List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("바람으로 자르기"))
        lore.add(Component.text("윈드 커터"))
        return lore
    }
}