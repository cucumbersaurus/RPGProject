package project.rpg.items.weapon.magic

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.items.weapon.MagicItemBase
import project.rpg.skill.magic.water.EternalFrost
import project.rpg.textComponets.color.TextColors

object EternalFrost : MagicItemBase() {
    override fun createItem() {
        item = ItemStack(Material.SNOWBALL).apply {
            itemMeta = itemMeta.apply {
                displayName(Component.text("寒冷").color(TextColors.DEEP_SKY_BLUE.color))
                lore(itemLore())
                setCustomModelData(Items.ETERNAL_FROST.value)
            }
        }
        skill = EternalFrost()
    }

    override fun onEnable(player: Player, action: Action?) {
        skill.onEnable(player, action)
    }

    private fun itemLore(): List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("이 세상의 것이 아닌 추움이다.."))
        lore.add(Component.text("이터널 프로스트"))
        return lore
    }
}