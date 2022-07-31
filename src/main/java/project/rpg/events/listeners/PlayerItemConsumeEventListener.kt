package project.rpg.events.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemConsumeEvent
import project.rpg.Rpg
import project.rpg.items.ItemType
import project.rpg.items.Items
import project.rpg.items.food.FoodBase
import project.rpg.items.potion.PotionBase
import project.rpg.player.User

class PlayerItemConsumeEventListener(private val _plugin: Rpg) : Listener {
    @EventHandler
    fun playerPotionDrinkEvent(event: PlayerItemConsumeEvent) {
        val player = event.player
        val mana = User.getPlayer(player).mana

        if (event.item.itemMeta.hasCustomModelData()) {
            val id = event.item.itemMeta.customModelData
            val item = Items.values()[id].itemBase
            if (item != null) {
                if (Items.values()[id].type == ItemType.POTION && item is PotionBase) {
                    item.onDrink(mana, _plugin, event)
                } else if (Items.values()[id].type  == ItemType.FOOD && item is FoodBase) {
                    item.onConsume(event)
                }
            }
        }
    }
}