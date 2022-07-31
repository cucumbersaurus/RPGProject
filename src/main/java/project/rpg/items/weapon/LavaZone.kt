package project.rpg.items.weapon

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.annotation.skill
import project.rpg.items.Items
import project.rpg.textComponets.color.TextColors

object LavaZone : WeaponBase() {

    override fun createItem() {
        val item = ItemStack(Material.LAVA_BUCKET)
        val meta = item.itemMeta
        skill = project.rpg.skill.magic.fire.LavaZone()

        meta.displayName(Component.text("화염 지대").color(TextColors.CRIMSON.color))
        meta.lore(itemLore())
        meta.setCustomModelData(Items.LAVA_ZONE.value)

        item.itemMeta = meta
        super.item = item
    }

    override fun onEnable(action: Action, player: Player) {
        skill.onEnable(player, action)
    }

    private fun itemLore():List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("화염 지대!"))
        return lore
    }

}