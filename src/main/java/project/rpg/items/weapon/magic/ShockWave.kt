package project.rpg.items.weapon.magic

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.items.weapon.MagicItemBase
import project.rpg.skill.magic.electricity.ShockWave
import project.rpg.textComponets.color.DefaultTextColors

object ShockWave : MagicItemBase() {
    override fun createItem() {
        val item = ItemStack(Material.YELLOW_BANNER)
        val meta = item.itemMeta
        skill = ShockWave()

        meta.displayName(Component.text("놀람 파도").color(DefaultTextColors.YELLOW.color))
        meta.lore(itemLore())
        meta.setCustomModelData(Items.SHOCK_WAVE.value)

        item.itemMeta = meta
        super.item = item
    }

    override fun onEnable(player: Player, action: Action) {
        skill.onEnable(player, action)
    }

    private fun itemLore():List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("파도~~"))
        lore.add(Component.text("쇼크 웨이브"))
        return lore
    }
}