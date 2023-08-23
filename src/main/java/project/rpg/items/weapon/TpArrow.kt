package project.rpg.items.weapon

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.skill.tmp.TpArrow
import project.rpg.textComponets.color.TextColors

object TpArrow : MagicItemBase() {
    override fun createItem() {
        val item = ItemStack(Material.ARROW)
        val meta = item.itemMeta
        skill = TpArrow()

        meta.displayName(Component.text("시공간의 화살").color(TextColor.color(TextColors.DEEP_SKY_BLUE.color)))
        meta.lore(itemLore())
        meta.setCustomModelData(Items.TP_ARROW.value)

        item.itemMeta = meta
        super.item = item
    }

    private fun itemLore(): List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("핑! 피용!"))
        return lore
    }

    override fun onEnable(player: Player, action: Action?) {
        skill.onEnable(player, action)
    }
}