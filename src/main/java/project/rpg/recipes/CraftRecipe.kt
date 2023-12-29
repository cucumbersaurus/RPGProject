package project.rpg.recipes

import org.bukkit.inventory.ItemStack

interface CraftRecipe {

    val recipe: Any
    val result: ItemStack

    /**
     * @return
     * clone of the result instance
     */
    val resultItem
        get() = result.clone()

    fun matches(matrix: List<ItemStack?>): CraftRecipe?


}