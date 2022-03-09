package rpgProject.ui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import rpgProject.baseClass.GuiBase;

public class EnchantTableUI extends GuiBase {


    protected EnchantTableUI(@NotNull Player p, int guiSize, String guiName) {
        super(p, guiSize, guiName);
    }

    @Override
    protected void init(@NotNull Player p) {

    }

    @Override
    public void onClick(InventoryClickEvent e) {

    }
}
