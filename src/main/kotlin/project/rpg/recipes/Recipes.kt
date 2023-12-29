package project.rpg.recipes

import project.rpg.recipes.blocks.Bedrock

enum class Recipes(val id: Int, val craftRecipe: CraftRecipe, val recipeType: RecipeType) {
    BEDROCK(0, Bedrock, RecipeType.SHAPED),

}

enum class RecipeType { AMORPHOUS, SHAPED, UNSHAPED }
