package project.rpg.items.weapon.magic

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.items.weapon.MagicItemBase
import project.rpg.skills.magic.electricity.ThunderCharging
import project.rpg.textComponets.color.TextColors

object ThunderCharging : MagicItemBase() {
    override fun createItem() {
        item = ItemStack(Material.SPECTRAL_ARROW).apply {
            itemMeta = itemMeta.apply {
                displayName(Component.text("전기 지지직").color(TextColors.LIGHT_YELLOW.color))
                lore(itemLore())
                setCustomModelData(Items.THUNDER_CHARGING.value)
            }
        }
        skill = ThunderCharging()
    }

    override fun onEnable(player: Player, action: Action?) {
        skill.onEnable(player, action)
    }

    private fun itemLore(): List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("천둥..?"))
        lore.add(Component.text("썬더 차징"))
        return lore
    }
}