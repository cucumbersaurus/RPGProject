package project.rpg.items.weapon.magic

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.items.weapon.MagicItemBase
import project.rpg.skills.magic.water.IceWave
import project.rpg.textComponets.color.TextColors

object IceWave : MagicItemBase() {
    override fun createItem() {
        item = ItemStack(Material.ICE).apply {
            itemMeta = itemMeta.apply {
                displayName(Component.text("얼음 파도").color(TextColors.TURQUOISE.color))
                lore(itemLore())
                setCustomModelData(Items.ICE_WAVE.value)
            }
        }
        skill = IceWave()
    }

    override fun onEnable(player: Player, action: Action?) {
        skill.onEnable(player, action)
    }

    private fun itemLore(): List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("I~C~E~ W~A~V~E~"))
        lore.add(Component.text("아이스 웨이브"))
        return lore
    }
}