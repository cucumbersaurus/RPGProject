package project.rpg.skill.base;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Operable {

    void onEnable(@NotNull Player player, @NotNull Action action);
}
