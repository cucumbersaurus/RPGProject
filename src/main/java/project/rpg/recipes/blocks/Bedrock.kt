package project.rpg.recipes.blocks

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import project.rpg.recipes.ShapedRecipe

object Bedrock: ShapedRecipe {

    private val obsidian = ItemStack(Material.OBSIDIAN)
    private val recipeData = listOf(obsidian, null, obsidian, null, obsidian, null, obsidian, null, obsidian)
    private val bedrock = ItemStack(Material.BEDROCK)

    override val recipe: List<ItemStack?>
        get() = recipeData
    override val result: ItemStack
        get() = bedrock
}