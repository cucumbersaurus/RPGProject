package project.rpg.events.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemConsumeEvent
import project.rpg.Rpg
import project.rpg.items.ItemType
import project.rpg.items.base.PotionBase
import project.rpg.manager.ItemManager.getItem
import project.rpg.manager.ItemManager.getType
import project.rpg.player.User

class PlayerPotionDrinkEventListener(private val _plugin: Rpg) : Listener {
    @EventHandler
    fun playerPotionDrinkEvent(event: PlayerItemConsumeEvent) {
        val player = event.player
        val mana = User.getPlayer(player).mana

        if (event.item.itemMeta.hasCustomModelData()) {
            val item = getItem(event.item.itemMeta.customModelData)
            if (item != null && getType(event.item.itemMeta.customModelData) === ItemType.POTION && item is PotionBase) {
                item.onDrink(mana, _plugin, event)
            }
        }
    }
}