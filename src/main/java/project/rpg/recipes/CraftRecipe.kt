package project.rpg.recipes

import org.bukkit.inventory.ItemStack

interface CraftRecipe {

    val recipe: Any
    fun matches(matrix: List<ItemStack?>): Boolean


}