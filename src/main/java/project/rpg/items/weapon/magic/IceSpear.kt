package project.rpg.items.weapon.magic

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.items.weapon.WeaponBase
import project.rpg.skill.magic.water.IceSpear
import project.rpg.textComponets.color.TextColors

object IceSpear : WeaponBase() {
    override fun createItem() {
        val item = ItemStack(Material.SPECTRAL_ARROW)
        val meta = item.itemMeta
        skill = IceSpear()

        meta.displayName(Component.text("얼음 화살").color(TextColors.SKY_BLUE.color))
        meta.lore(itemLore())
        meta.setCustomModelData(Items.ICE_SPEAR.value)

        item.itemMeta = meta
        super.item = item
    }

    override fun onEnable(action: Action, player: Player) {
        skill.onEnable(player, action)
    }

    private fun itemLore():List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("앗 얼었다"))
        lore.add(Component.text("아이스 스피오"))
        return lore
    }
}