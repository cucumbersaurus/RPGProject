package project.rpg.recipes

import org.bukkit.inventory.ItemStack

interface UnshapedRecipe : CraftRecipe {

    override val recipe: HashMap<ItemStack, Int>
    override fun matches(matrix: List<ItemStack?>): Boolean {
        val comp = recipe.clone() as HashMap<ItemStack, Int>
        matrix.forEach {
            if (it != null) {
                if (comp[it] == null) return false
                else if (comp[it] == 0) return false
                else comp[it] = comp[it]!! - 1
            }
        }
        comp.forEach {
            if (it.value != 0) return false
        }
        return true
    }
}