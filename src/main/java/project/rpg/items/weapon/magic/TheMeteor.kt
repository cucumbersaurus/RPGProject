package project.rpg.items.weapon.magic

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.items.weapon.MagicItemBase
import project.rpg.skill.magic.fire.MeteorStrike
import project.rpg.textComponets.color.DefaultTextColors

object TheMeteor: MagicItemBase() {
    override fun createItem() {
        item = ItemStack(Material.FIRE_CHARGE).apply {
            itemMeta = itemMeta.apply {
                displayName(text("휴대용 메테오").color(TextColor.color(DefaultTextColors.GOLD.color)))
                lore(itemLore())
                setCustomModelData(Items.THE_METEOR.value)
            }
        }
        skill = MeteorStrike()
    }

    override fun onEnable(player: Player, action: Action?) {
        skill.onEnable(player, action)
    }

    private fun itemLore():List<Component> {
        val lore = ArrayList<Component>()
        lore.add(text("펑펑!!"))
        lore.add(text("메테오 스트라이크"))
        return lore
    }
}