package project.rpg.recipes

import org.bukkit.inventory.ItemStack

interface ShapedRecipe : CraftRecipe {

    override val recipe: List<ItemStack?>
    override fun matches(matrix: List<ItemStack?>): CraftRecipe? {
        if (recipe.size != matrix.size) return null
        for (i in matrix.indices) {

            val item = matrix[i]?.clone()
            item?.amount=1
            if (recipe[i] != item) return null
        }
        //TODO("개수 여러개일때 무한 복사 오류 있음")
        return this
    }
}