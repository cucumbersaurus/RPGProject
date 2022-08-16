package project.rpg.items.weapon.magic

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.items.weapon.MagicItemBase
import project.rpg.skill.magic.wind.HeavenWing
import project.rpg.textComponets.color.TextColors

object HeavenWing : MagicItemBase() {
    override fun createItem() {
        item = ItemStack(Material.PHANTOM_MEMBRANE).apply {
            itemMeta = itemMeta.apply {
                displayName(Component.text("날아올라!~").color(TextColors.TURQUOISE.color))
                lore(itemLore())
                setCustomModelData(Items.HEAVEN_WING.value)
            }
        }
        skill = HeavenWing()
    }

    override fun onEnable(player: Player, action: Action?) {
        skill.onEnable(player, action)
    }

    private fun itemLore():List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("저 하늘 높이"))
        lore.add(Component.text("헤븐 윙"))
        return lore
    }
}