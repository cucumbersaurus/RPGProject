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
import project.rpg.textComponets.color.TextColors

object HighEnrichmentCandy : FoodBase() {
    override fun createItem() {
        val item = ItemStack(Material.BEETROOT)
        val meta = item.itemMeta

        meta.displayName(Component.text("고농축 사탕").color(TextColors.STEEL_BLUE.color))
        meta.lore(itemLore())
        meta.setCustomModelData(Items.HIGH_ENRICHMENT_CANDY.value)
        meta.addEnchant(Enchantment.LURE, 1, true)
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS)

        item.itemMeta = meta
        super.item = item
    }

    override fun onConsume(event: PlayerItemConsumeEvent?, hunger:Int, saturation:Int) {
        val player = event!!.player
        player.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, 15*20, 5, true,true,true))
        super.onConsume(event,hunger,saturation)
    }

    override fun onConsume(event: PlayerItemConsumeEvent?) {
        onConsume(event,2,5)
    }

    private fun itemLore():List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("할일 없던 연금술사가 포션을 가마솥에 넣고 오랫동안 졸여서 만든 사탕이다."))
        lore.add(Component.text("15초간 체력 초당 10 회복 (효과 중첩 가능)"))
        return lore
    }
}