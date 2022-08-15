package project.rpg.extensions

import net.kyori.adventure.text.Component
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

fun ItemStack.setDisplayName(name:Component?) = this.itemMeta.displayName(name)

fun ItemStack.setGlow(){
    this.itemMeta.apply {
        addEnchant(Enchantment.LURE, 0, true)
        addItemFlags(ItemFlag.HIDE_ENCHANTS)
    }
}

fun ItemStack.setCustomModelData(modelData:Int) = this.itemMeta.setCustomModelData(modelData)