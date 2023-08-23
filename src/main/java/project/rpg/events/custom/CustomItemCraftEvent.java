package project.rpg.events.custom;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import project.rpg.Rpg;

public class CustomItemCraftEvent extends Event {

    public static final HandlerList _handlerList = new HandlerList();
    private Rpg _plugin;

    public static HandlerList getHandlerList() {
        return _handlerList;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return _handlerList;
    }

    public void pluginEnableEvent(Rpg plugin) {
        _plugin = plugin;
    }

}
