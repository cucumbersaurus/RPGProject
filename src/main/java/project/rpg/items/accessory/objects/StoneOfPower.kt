package project.rpg.items.accessory.objects

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import project.rpg.items.accessory.AccessoryBase
import project.rpg.textComponets.color.TextColors

class StoneOfPower : AccessoryBase() {
    override fun onLift() {
        TODO("Not yet implemented")
    }

    override fun inInventory() {
        TODO("Not yet implemented")
    }

    override fun createItem() {
        item = ItemStack(Material.CHAIN).apply {
            itemMeta = itemMeta.apply {
                displayName(Component.text("힘의 돌").color(TextColors.FIRE_BRICK.color))
                lore(itemLore())
            }
        }
    }

    private fun itemLore() : List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("인벤토리에 들고 있으면 힘 스텟이 10 늘어난 것과 같다."))
        lore.add(Component.text("와 정말 신기해"))
        return lore
    }
}