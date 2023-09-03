package project.rpg.recipes

import org.bukkit.inventory.ItemStack

interface ShapedRecipe : CraftRecipe {

    override val recipe: List<ItemStack?>
    override fun matches(matrix: List<ItemStack?>): CraftRecipe? {
        require(recipe.size == matrix.size) { "recipe size mismatch" }
        for (i in matrix.indices) {

            val item = matrix[i]?.clone()
            item?.amount=1
            //if(recipe[i]!=null && mat[i]!=null && recipe[i]!!.type == mat[i]!!.type && recipe[i]!!.amount > mat[i]!!.amount) return null
            if (recipe[i] != item) return null
        }
        return this
        TODO("개수 여러개일때 무한 복사 오류 있음")
    }
}