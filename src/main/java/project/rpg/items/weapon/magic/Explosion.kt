package project.rpg.items.weapon.magic

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.annotation.skill
import project.rpg.items.Items
import project.rpg.items.weapon.MagicItemBase
import project.rpg.textComponets.color.TextColors

object Explosion : MagicItemBase() {

    override fun createItem() {
        item = ItemStack(Material.FIRE_CHARGE).apply {
            itemMeta = itemMeta.apply {
                displayName(Component.text("☆폭발☆").color(TextColors.MAROON.color))
                lore(itemLore())
                setCustomModelData(Items.EXPLOSION.value)
            }
        }
        skill = project.rpg.skill.magic.fire.FlameBurst()
    }

    @skill(name = "flame_burst")
    override fun onEnable(player: Player, action: Action?) {
        skill.onEnable(player, action)
    }

    private fun itemLore(): List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("펑펑 터져라"))
        lore.add(Component.text("익스플로젼"))
        return lore
    }
}