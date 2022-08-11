package project.rpg.items.weapon.magic

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.items.weapon.MagicItemBase
import project.rpg.skill.magic.electricity.GiganticThunder
import project.rpg.textComponets.color.TextColors

object GiganticThunder : MagicItemBase() {
    override fun createItem() {
        val item = ItemStack(Material.YELLOW_CANDLE)
        val meta = item.itemMeta
        skill = GiganticThunder()

        meta.displayName(Component.text("거대 번개").color(TextColors.PALE_GOLDEN_ROD.color))
        meta.lore(itemLore())
        meta.setCustomModelData(Items.GIGANTIC_THUNDER.value)

        item.itemMeta = meta
        super.item = item
    }

    override fun onEnable(player: Player, action: Action?) {
        skill.onEnable(player, action)
    }

    private fun itemLore():List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("번개여 쳐라"))
        lore.add(Component.text("기간트 썬더"))
        return lore
    }
}