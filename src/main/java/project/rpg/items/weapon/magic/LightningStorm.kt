package project.rpg.items.weapon.magic

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.items.weapon.WeaponBase
import project.rpg.skill.magic.electricity.LightningStorm
import project.rpg.textComponets.color.TextColors

object LightningStorm : WeaponBase() {
    override fun createItem() {
        val item = ItemStack(Material.IRON_INGOT)
        val meta = item.itemMeta
        skill = LightningStorm()

        meta.displayName(Component.text("번개 구름").color(TextColors.GOLDEN_ROD.color))
        meta.lore(itemLore())
        meta.setCustomModelData(Items.LIGHTNING_STORM.value)

        item.itemMeta = meta
        super.item = item
    }

    override fun onEnable(action: Action, player: Player) {
        skill.onEnable(player, action)
    }

    private fun itemLore():List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("찌리 찌리 짜라 짜라"))
        lore.add(Component.text("라이트닝 스톰"))
        return lore
    }
}