package project.rpg.ui.inventory.blockInterection;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import project.rpg.ui.inventory.GuiBase;

import static net.kyori.adventure.text.Component.text;

public class EnchantUI extends GuiBase {


    protected EnchantUI(@NotNull Player p) {
        super(p, 54, text("마법부여대"));
    }

    @Override
    protected void initialize(@NotNull Player player) {

    }

    @Override
    public void onClick(@NotNull InventoryClickEvent event) {

    }
}
