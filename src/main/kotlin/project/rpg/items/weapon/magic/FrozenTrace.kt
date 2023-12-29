package project.rpg.items.weapon.magic

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.items.weapon.MagicItemBase
import project.rpg.skills.magic.water.FrozenTrace
import project.rpg.textComponets.color.TextColors

object FrozenTrace : MagicItemBase() {
    override fun createItem() {
        item = ItemStack(Material.DIAMOND_BOOTS).apply {
            itemMeta = itemMeta.apply {
                displayName(Component.text("돌진!").color(TextColors.CADET_BLUE.color))
                lore(itemLore())
                setCustomModelData(Items.FROZEN_TRACE.value)
            }
        }
        skill = FrozenTrace()
    }

    override fun onEnable(player: Player, action: Action?) {
        skill.onEnable(player, action)
    }

    private fun itemLore(): List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("얼음 길만 걸어"))
        lore.add(Component.text("프로즌 트레이스"))
        return lore
    }
}