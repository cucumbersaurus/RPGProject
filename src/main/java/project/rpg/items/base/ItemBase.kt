package project.rpg.items.base

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

abstract class ItemBase {
    var item: ItemStack? = null
        protected set

    abstract fun createItem()
}