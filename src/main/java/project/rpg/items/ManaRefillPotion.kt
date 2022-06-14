package project.rpg.items

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import project.rpg.items.base.ItemBase
import java.util.*

object ManaRefillPotion : ItemBase() {
    init {
        createItem()
    }

    override fun createItem() {
        val item = ItemStack(Material.POTION, 1)
        val meta = item.itemMeta

        //display name
        meta.setDisplayName("마나 회복 포션")
        //lore
        meta.lore =
            ArrayList(Arrays.asList("이 포션을 마시면", "최대 100의 마나를 회복합니다"))
        //아이템 아이디 설정
        meta.setCustomModelData(ItemType.MANA_REFILLING_POTION.value)
        item.itemMeta = meta
        _item = item
    }
}