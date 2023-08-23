package project.rpg.items.weapon.magic

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.annotation.skill
import project.rpg.items.Items
import project.rpg.items.weapon.MagicItemBase
import project.rpg.skill.magic.fire.BlazingMark
import project.rpg.textComponets.color.TextColors

object BlazingMark : MagicItemBase() {

    override fun createItem() {
        item = ItemStack(Material.BLAZE_ROD).apply {
            itemMeta = itemMeta.apply {
                displayName(Component.text("타오르는 표식").color(TextColors.CRIMSON.color))
                lore(itemLore())
                setCustomModelData(Items.BLAZING_MARK.value)
            }
        }
        skill = BlazingMark()
    }

    @skill(name = "blazing_mark")
    override fun onEnable(player: Player, action: Action?) {
        skill.onEnable(player, action)
    }

    private fun itemLore(): List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("앗 뜨거ㅓㅓ"))
        lore.add(Component.text("블레이징 마크"))
        return lore
    }
}