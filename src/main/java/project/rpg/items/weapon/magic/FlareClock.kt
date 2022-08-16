package project.rpg.items.weapon.magic

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.items.weapon.MagicItemBase
import project.rpg.skill.magic.fire.FlareClock
import project.rpg.textComponets.color.DefaultTextColors

object FlareClock : MagicItemBase() {

    override fun createItem() {
        item = ItemStack(Material.CLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName(Component.text("플레어 시계").color(DefaultTextColors.RED.color))
                lore(itemLore())
                setCustomModelData(Items.FLARE_CLOCK.value)
            }
        }
        skill = FlareClock()
    }

    override fun onEnable(player: Player, action: Action?) {
        skill.onEnable(player, action)
    }

    private fun itemLore():List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("폭팔!!"))
        lore.add(Component.text("파이어 클락"))
        return lore
    }
}