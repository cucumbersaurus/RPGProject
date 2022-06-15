package project.rpg.ui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import project.rpg.base.GuiBase;

import static net.kyori.adventure.text.Component.text;

public class EnchantTableUI extends GuiBase {


    protected EnchantTableUI(@NotNull Player p) {
        super(p, 54, text("마법부여대"));
    }

    @Override
    protected void init(@NotNull Player player) {

    }

    @Override
    public void onClick(InventoryClickEvent event) {

    }
}
