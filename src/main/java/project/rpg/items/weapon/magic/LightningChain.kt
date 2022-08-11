package project.rpg.items.weapon.magic

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.items.weapon.MagicItemBase
import project.rpg.skill.magic.electricity.LightningChain
import project.rpg.textComponets.color.TextColors

object LightningChain : MagicItemBase() {
    override fun createItem() {
        val item = ItemStack(Material.CHAINMAIL_BOOTS)
        val meta = item.itemMeta
        skill = LightningChain()

        meta.displayName(Component.text("전기 사슬?").color(TextColors.BISQUE.color))
        meta.lore(itemLore())
        meta.setCustomModelData(Items.LIGHTNING_CHAIN.value)

        item.itemMeta = meta
        super.item = item
    }

    override fun onEnable(player: Player, action: Action?) {
        skill.onEnable(player, action)
    }

    private fun itemLore():List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("지릭 .. 지릭 .... 지릭.. 지릭"))
        lore.add(Component.text("라이트닝 체인"))
        return lore
    }
}