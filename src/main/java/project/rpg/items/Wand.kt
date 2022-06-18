package project.rpg.items

import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import project.rpg.items.base.ItemBase

object Wand : ItemBase() {

    override fun createItem() {
        val item = ItemStack(Material.STICK, 1)
        val meta = item.itemMeta

        //display name
        meta.displayName(text("전설의 막대기").color(TextColor.color(0xff55ff)))
        //lore
        meta.lore =
            ArrayList(listOf("이 막대기는 아주 위대하고", "전설적인 나무젓가락 입니다"))
        //enchantments
        meta.addEnchant(Enchantment.LUCK, 1, true)
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        //아이템 아이디 설정
        meta.setCustomModelData(Items.WAND.value)
        item.itemMeta = meta
        super.item = item
    }
}