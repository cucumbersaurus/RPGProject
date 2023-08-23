package project.rpg.items.weapon.magic

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.items.weapon.MagicItemBase
import project.rpg.skill.magic.water.IceSpear
import project.rpg.textComponets.color.TextColors

object IceSpear : MagicItemBase() {
    override fun createItem() {
        item = ItemStack(Material.SPECTRAL_ARROW).apply {
            itemMeta = itemMeta.apply {
                displayName(Component.text("얼음 화살").color(TextColors.SKY_BLUE.color))
                lore(itemLore())
                setCustomModelData(Items.ICE_SPEAR.value)
            }
        }
        skill = IceSpear()
    }

    override fun onEnable(player: Player, action: Action?) {
        skill.onEnable(player, action)
    }

    private fun itemLore(): List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("앗 얼었다"))
        lore.add(Component.text("아이스 스피오"))
        return lore
    }
}