package project.rpg.items.food

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import project.rpg.items.Items
import project.rpg.textComponets.color.DefaultTextColors

object SpeedApple : FoodBase() {
    override fun createItem() {
        val item = ItemStack(Material.APPLE)
        val meta = item.itemMeta

        meta.displayName(Component.text("바람의 사과").color(DefaultTextColors.AQUA.color))
        meta.lore(itemLore())
        meta.setCustomModelData(Items.SPEED_APPLE.value)
        meta.addEnchant(Enchantment.LURE, 1, true)
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS)

        item.itemMeta = meta
        super.item = item
    }

    override fun onConsume(event: PlayerItemConsumeEvent?, hunger:Int, saturation:Int) {
        val player = event!!.player
        player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 30*20, 2, true,true,true))
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