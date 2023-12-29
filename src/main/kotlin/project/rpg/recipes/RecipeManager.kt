package project.rpg.recipes

import org.bukkit.inventory.ItemStack
import kotlin.math.min

object RecipeManager {

    private val recipeDictionary = HashMap<Recipes, CraftRecipe>()

    fun initialize() {
        for (i in Recipes.values()) {
            recipeDictionary[i] = i.craftRecipe
        }
    }

    fun getRecipe(matrix: List<ItemStack?>): CraftRecipe?{
        var recipe: CraftRecipe? = null
        recipeDictionary.forEach { (_, craftRecipe) ->
            recipe = craftRecipe.matches(matrix)
            if(recipe!=null){
                return@forEach
            }
        }
        return recipe
    }

    fun getMaxRecipeCount(matrix: List<ItemStack?>):Int{
        var min = 0//초기화 해야해서 0으로 둠

        for(i in matrix){
            if(i!=null){
                min = i.amount//아이템이 하나라도 있으면 1로 초기화됨
                break
            }
        }
        for (i in matrix){
            if(i!=null){
                min = min(min, i.amount)
            }
        }
        return min
    }
}