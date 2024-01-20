package project.rpg.items.potion

import org.bukkit.event.player.PlayerItemConsumeEvent
import project.rpg.items.ItemBase
import project.rpg.player.mana.Mana

abstract class PotionBase : ItemBase() {
    abstract fun onDrink(mana: Mana, event: PlayerItemConsumeEvent)
}