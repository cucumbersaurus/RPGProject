package project.rpg.items.weapon.magic

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.items.weapon.MagicItemBase
import project.rpg.skills.magic.fire.Inferno
import project.rpg.textComponets.color.TextColors

object Inferno : MagicItemBase() {

    override fun createItem() {
        item = ItemStack(Material.MAGMA_CREAM).apply {
            itemMeta = itemMeta.apply {
                displayName(Component.text("레이져 빔!").color(TextColors.INDIAN_RED.color))
                lore(itemLore())
                setCustomModelData(Items.INFERNO.value)
            }
        }
        skill = Inferno()
    }

    override fun onEnable(player: Player, action: Action?) {
        skill.onEnable(player, action)
    }

    private fun itemLore(): List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("인페르노 타워"))
        lore.add(Component.text("인페르노"))
        return lore
    }

}