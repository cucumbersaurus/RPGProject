package project.rpg.items.weapon

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.annotation.skill
import project.rpg.items.Items
import project.rpg.skill.magic.fire.BlazingMark
import project.rpg.textComponets.color.TextColors

object BlazingMark : WeaponBase() {

    override fun createItem() {
        val item = ItemStack(Material.BLAZE_ROD)
        val meta = item.itemMeta
        skill = BlazingMark()

        meta.displayName(Component.text("타오르는 표식").color(TextColors.CRIMSON.color))
        meta.lore(itemLore())
        meta.setCustomModelData(Items.BLAZING_MARK.value)

        item.itemMeta = meta
        super.item = item
    }

    @skill(name = "blazing_mark")
    override fun onEnable(action: Action?, player: Player) {
        skill.onEnable(player, action)
    }

    private fun itemLore():List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("앗 뜨거ㅓㅓ"))
        return lore
    }
}