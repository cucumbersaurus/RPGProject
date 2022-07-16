package project.rpg.events.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemConsumeEvent
import project.rpg.Rpg
import project.rpg.items.Items
import project.rpg.manager.ItemManager.isEquals
import project.rpg.player.User

class PlayerPotionDrinkEventListener(private val _plugin: Rpg) : Listener {
    @EventHandler
    fun playerPotionDrinkEvent(event: PlayerItemConsumeEvent) {
        val player = event.player
        val mana = User.getPlayer(player).mana
        if (isEquals(event.item, Items.MANA_REFILLING_POTION.item!!)) {
            val leftUntilFull = mana.maxMana - mana.mana
            if (leftUntilFull >= 100) {
                mana.addMana(100)
            } else {
                mana.addMana(mana.maxMana - mana.mana)
            }
            _plugin.actionBar.updateActionBar(player)
        }
    }
}