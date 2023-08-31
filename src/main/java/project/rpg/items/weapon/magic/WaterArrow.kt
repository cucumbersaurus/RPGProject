package project.rpg.items.weapon.magic

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.items.weapon.MagicItemBase
import project.rpg.skills.magic.water.WaterArrow
import project.rpg.textComponets.color.TextColors

object WaterArrow : MagicItemBase() {
    override fun createItem() {
        item = ItemStack(Material.ARROW).apply {
            itemMeta = itemMeta.apply {
                displayName(Component.text("물화살").color(TextColors.ALICE_BLUE.color))
                lore(itemLore())
                setCustomModelData(Items.WATER_ARROW.value)
            }
        }
        skill = WaterArrow()
    }

    override fun onEnable(player: Player, action: Action?) {
        skill.onEnable(player, action)
    }

    private fun itemLore(): List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("앗 차가워"))
        lore.add(Component.text("워터 에로우"))
        return lore
    }
}