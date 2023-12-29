package project.rpg.items.material

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import project.rpg.items.GeneralItemBase
import project.rpg.items.Items

object HardRock: GeneralItemBase() {
    override fun createItem() {
        val item = ItemStack(Material.OBSIDIAN, 1)
        val meta = item.itemMeta

        //display name
        meta.displayName(Component.text("단단한 돌").color(TextColor.color(0x21252b)))
        //lore
        meta.lore(ArrayList(listOf(
            Component.text("세상에서 두번째로 단단한 돌"),
            Component.text("뭉치면 더 단단해진다")
        )) as List<Component>?)

        //enchantments
        meta.addEnchant(Enchantment.LURE, 1, true)
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        //아이템 아이디 설정
        meta.setCustomModelData(Items.HARD_ROCK.value)
        item.itemMeta = meta
        super.item = item
    }
}