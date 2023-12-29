package project.rpg.items.weapon.magic

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.items.weapon.MagicItemBase
import project.rpg.skills.magic.electricity.LightningChain
import project.rpg.textComponets.color.TextColors

object LightningChain : MagicItemBase() {
    override fun createItem() {
        item = ItemStack(Material.CHAINMAIL_BOOTS).apply {
            itemMeta = itemMeta.apply {
                displayName(Component.text("전기 사슬?").color(TextColors.BISQUE.color))
                lore(itemLore())
                setCustomModelData(Items.LIGHTNING_CHAIN.value)
            }
        }
        skill = LightningChain()
    }

    override fun onEnable(player: Player, action: Action?) {
        skill.onEnable(player, action)
    }

    private fun itemLore(): List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("지릭 .. 지릭 .... 지릭.. 지릭"))
        lore.add(Component.text("라이트닝 체인"))
        return lore
    }
}