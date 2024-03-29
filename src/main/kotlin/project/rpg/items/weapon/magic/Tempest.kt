package project.rpg.items.weapon.magic

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.items.weapon.MagicItemBase
import project.rpg.skills.magic.wind.Tempest
import project.rpg.textComponets.color.TextColors

object Tempest : MagicItemBase() {
    override fun createItem() {
        item = ItemStack(Material.OXEYE_DAISY).apply {
            itemMeta = itemMeta.apply {
                displayName(Component.text("나는 바람이다").color(TextColors.PALE_TURQUOISE.color))
                lore(itemLore())
                setCustomModelData(Items.TEMPEST.value)
            }
        }
        skill = Tempest()
    }

    override fun onEnable(player: Player, action: Action?) {
        skill.onEnable(player, action)
    }

    private fun itemLore(): List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("바람의 힘!"))
        lore.add(Component.text("탬페스트"))
        return lore
    }
}