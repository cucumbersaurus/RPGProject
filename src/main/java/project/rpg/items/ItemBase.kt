package project.rpg.items

import org.bukkit.inventory.ItemStack

abstract class ItemBase {
    var item: ItemStack? = null
        protected set

    abstract fun createItem()
}