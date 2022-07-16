package project.rpg.items

import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.PotionMeta
import project.rpg.items.base.PotionBase

object ManaRefillPotion : PotionBase() {
    override fun createItem() {
        val item = ItemStack(Material.POTION, 1)
        val meta = item.itemMeta as PotionMeta

        //display name
        meta.setDisplayName("마나 회복 포션")
        //lore
        meta.lore =
            ArrayList(listOf("이 포션을 마시면", "최대 100의 마나를 회복합니다"))
        //아이템 아이디 설정
        meta.setCustomModelData(Items.MANA_REFILLING_POTION.value)

        meta.color = Color.AQUA

        item.itemMeta = meta
        super.item = item
    }
}