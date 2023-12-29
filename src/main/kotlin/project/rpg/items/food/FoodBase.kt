package project.rpg.items.food

import org.bukkit.event.player.PlayerItemConsumeEvent
import project.rpg.items.ItemBase

abstract class FoodBase : ItemBase() {
    abstract fun onConsume(event: PlayerItemConsumeEvent)

    open fun onConsume(event: PlayerItemConsumeEvent, hunger: Int, saturation: Int) {
        val player = event.player
        player.saturation = player.saturation + saturation
        player.foodLevel = hunger
    }
}
