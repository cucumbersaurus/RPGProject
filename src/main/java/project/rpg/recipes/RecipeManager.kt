package project.rpg.recipes

import org.bukkit.inventory.ItemStack

object RecipeManager {

    private val recipeDictionary = HashMap<Recipes, CraftRecipe?>()

    fun initialize() {
        for (i in Recipes.values()) {
            recipeDictionary[i] = i.craftRecipe
        }
    }

    fun getRecipe(matrix: List<ItemStack?>): CraftRecipe?{
        var recipe: CraftRecipe? = null
        recipeDictionary.forEach { (_, craftRecipe) ->
            recipe = craftRecipe!!.matches(matrix)
            if(recipe!=null){
                return@forEach
            }
        }
        return recipe
    }
}