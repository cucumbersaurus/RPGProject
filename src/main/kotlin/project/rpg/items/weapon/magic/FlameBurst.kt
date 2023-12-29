package project.rpg.items.weapon.magic

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.items.weapon.MagicItemBase
import project.rpg.skills.magic.fire.FlameBurst
import project.rpg.textComponets.color.TextColors

object FlameBurst : MagicItemBase() {

    override fun createItem() {
        item = ItemStack(Material.FIRE_CHARGE).apply {
            itemMeta = itemMeta.apply {
                displayName(Component.text("화염 폭발").color(TextColors.MAROON.color))
                lore(itemLore())
                setCustomModelData(Items.FLAME_BURST.value)
            }
        }
        skill = FlameBurst()
    }

    override fun onEnable(player: Player, action: Action?) {
        skill.onEnable(player, action)
    }

    private fun itemLore(): List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("와 배워 화염구다~"))
        lore.add(Component.text("플레임 버스트"))
        return lore
    }

}