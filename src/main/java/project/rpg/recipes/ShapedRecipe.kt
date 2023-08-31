package project.rpg.recipes

import org.bukkit.inventory.ItemStack

interface ShapedRecipe : CraftRecipe {

    override val recipe: List<ItemStack>
    override fun matches(matrix: List<ItemStack?>): Boolean {
        if (recipe.size != matrix.size) throw IllegalArgumentException("recipe size mismatch")
        else {
            for (i in matrix.indices) {
                if (recipe[i] != matrix[i]) return false
            }
        }
        return true
    }
}