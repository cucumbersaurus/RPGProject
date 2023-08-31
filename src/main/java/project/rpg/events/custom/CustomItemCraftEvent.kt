package project.rpg.events.custom

import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import project.rpg.Rpg

class CustomItemCraftEvent : Event() {
    override fun getHandlers() = HANDLER_LIST

    companion object {
        @JvmStatic
        val HANDLER_LIST = HandlerList()

        @JvmStatic
        fun getHandlerList() = HANDLER_LIST
    }

}
