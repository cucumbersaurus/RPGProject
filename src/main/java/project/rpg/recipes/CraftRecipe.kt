package project.rpg.recipes

import org.bukkit.inventory.ItemStack

interface CraftRecipe {

    val recipe: Any
    val result: ItemStack
    fun matches(matrix: List<ItemStack?>): CraftRecipe?


}