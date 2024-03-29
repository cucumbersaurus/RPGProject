package project.rpg.items

import net.kyori.adventure.text.Component
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

var ItemStack.customModelData: Int
    get() = itemMeta.customModelData
    set(value) {
        itemMeta = itemMeta.apply {
            itemMeta.setCustomModelData(value)
        }
    }

val ItemStack.hasCustomModelData: Boolean
    get() = itemMeta.hasCustomModelData()

/**
 * itemMeta를 연속적으로 조작시 성능 저하 가능성 있음
 */
fun ItemStack.setDisplayName(name: Component?) {
    itemMeta = itemMeta.apply {
        displayName(name)
    }
}


/**
 * itemMeta를 연속적으로 조작시 성능 저하 가능성 있음
 */
fun ItemStack.setGlow() {
    itemMeta = itemMeta.apply {
        addEnchant(Enchantment.LURE, 0, true)
        addItemFlags(ItemFlag.HIDE_ENCHANTS)
    }
}