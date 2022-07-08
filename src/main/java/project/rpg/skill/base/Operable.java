package project.rpg.skill.base;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

public interface Operable {

    void onEnable(Player player, Action action);
}
