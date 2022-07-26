package project.rpg.items.objects

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import project.rpg.items.Items
import project.rpg.items.base.FoodBase
import project.rpg.textComponets.color.DefaultTextColors

object SpeedApple : FoodBase() {
    override fun createItem() {
        val item = ItemStack(Material.APPLE)
        val meta = item.itemMeta

        meta.displayName(Component.text("속도 사과").color(DefaultTextColors.AQUA.color))
        meta.lore(SpeedApple.itemLore())
        meta.setCustomModelData(Items.SPEED_APPLE.value)

        item.itemMeta = meta
        super.item = item
    }

    override fun onConsume(event: PlayerItemConsumeEvent?, hunger:Int, saturation:Int) {
        val player = event!!.player
        player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 30, 3, true,true,true))
        super.onConsume(event,hunger,saturation)
    }

    override fun onConsume(event: PlayerItemConsumeEvent?) {
        onConsume(event,10,10)
    }

    private fun itemLore():List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("냠냠"))
        lore.add(Component.text("맛있다"))
        return lore
    }
}