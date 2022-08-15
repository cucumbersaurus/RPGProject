package project.rpg.items.weapon.magic

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.items.weapon.MagicItemBase
import project.rpg.skill.magic.earth.Binding
import project.rpg.textComponets.color.TextColors

object Binding : MagicItemBase() {
    override fun createItem() {

        super.item = ItemStack(Material.CHAIN).apply {
            itemMeta=itemMeta.apply {
                displayName(Component.text("봉쇄").color(TextColors.DIM_GRAY.color))
                lore(itemLore())
                setCustomModelData(Items.BINDING.value)
            }
        }
        skill = Binding()
    }

    override fun onEnable(player: Player, action: Action?) {
        skill.onEnable(player, action)
    }

    private fun itemLore():List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("봉쇄죠?"))
        lore.add(Component.text("바인딩"))
        return lore
    }
}