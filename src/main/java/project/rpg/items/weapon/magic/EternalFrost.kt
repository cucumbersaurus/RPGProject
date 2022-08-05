package project.rpg.items.weapon.magic

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.items.weapon.WeaponBase
import project.rpg.skill.magic.water.EternalFrost
import project.rpg.textComponets.color.TextColors

object EternalFrost : WeaponBase() {
    override fun createItem() {
        val item = ItemStack(Material.SNOWBALL)
        val meta = item.itemMeta
        skill = EternalFrost()

        meta.displayName(Component.text("寒冷").color(TextColors.DEEP_SKY_BLUE.color))
        meta.lore(itemLore())
        meta.setCustomModelData(Items.ETERNAL_FROST.value)

        item.itemMeta = meta
        super.item = item
    }

    override fun onEnable(action: Action, player: Player) {
        skill.onEnable(player, action)
    }

    private fun itemLore():List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("이 세상의 것이 아닌 추움이다.."))
        lore.add(Component.text("이터널 프로스트"))
        return lore
    }
}