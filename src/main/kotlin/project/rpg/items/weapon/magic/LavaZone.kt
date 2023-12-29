package project.rpg.items.weapon.magic

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.items.weapon.MagicItemBase
import project.rpg.textComponets.color.TextColors

object LavaZone : MagicItemBase() {

    override fun createItem() {
        item = ItemStack(Material.LAVA_BUCKET).apply {
            itemMeta = itemMeta.apply {
                displayName(Component.text("화염 지대").color(TextColors.CRIMSON.color))
                lore(itemLore())
                setCustomModelData(Items.LAVA_ZONE.value)
            }
        }
        skill = project.rpg.skills.magic.fire.LavaZone()
    }

    override fun onEnable(player: Player, action: Action?) {
        skill.onEnable(player, action)
    }

    private fun itemLore(): List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("화염 지대!"))
        lore.add(Component.text("라바존"))
        return lore
    }

}