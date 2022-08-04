package project.rpg.items.weapon.magic

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.TextColor.color
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.items.weapon.WeaponBase
import project.rpg.skill.magic.fire.MeteorStrike
import project.rpg.textComponets.color.DefaultTextColors

object TheMeteor: WeaponBase() {
    override fun createItem() {
        val item = ItemStack(Material.FIRE_CHARGE)
        val meta = item.itemMeta
        skill = MeteorStrike()

        meta.displayName(text("휴대용 메테오").color(color(DefaultTextColors.GOLD.color)))
        meta.lore(itemLore())
        meta.setCustomModelData(Items.THE_METEOR.value)

        item.itemMeta = meta
        super.item = item
    }

    override fun onEnable(action: Action, player: Player) {
        skill.onEnable(player, action)
    }

    private fun itemLore():List<Component> {
        val lore = ArrayList<Component>()
        lore.add(text("펑펑!!"))
        lore.add(text("메테오 스트라이크"))
        return lore
    }
}