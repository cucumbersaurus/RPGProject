package rpgProject.baseClass;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GuiBase {

    protected static final Map<Player,GuiBase> guiMap = new HashMap<>();
    protected Inventory inv;
    protected Map<Integer,String> slotMap;

    public static GuiBase getGUI(Player p) { return guiMap.getOrDefault(p, null); }

    protected GuiBase(@NotNull Player p, int guiSize, String guiName) {
        inv = Bukkit.createInventory(null, guiSize, guiName);
        slotMap = new HashMap<Integer, String>();
        init(p);
        p.openInventory(inv);
        guiMap.put(p, this);
    }

    protected abstract void init(@NotNull Player p);
    public abstract void onClick(InventoryClickEvent e);

    protected void setItem(String name, List<String> lore, @NotNull Material m, int amount, int slot, String value, boolean isGlow) {
        ItemStack item = new ItemStack(m,amount);
        ItemMeta meta = item.getItemMeta();
        if(name != null) meta.setDisplayName(name);
        if(lore != null) meta.setLore(lore);
        if(isGlow) {
            meta.addEnchant(Enchantment.LURE, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        item.setItemMeta(meta);
        slotMap.put(slot, value);
        inv.setItem(slot, item);
    }
    protected void setItem(ItemStack item, int slot, String value){
        slotMap.put(slot, value);
        inv.setItem(slot, item);
    }

    protected String getValue(int slot) {return slotMap.getOrDefault(slot, null);}

    public void closeGUI(@NotNull InventoryCloseEvent e) {
        slotMap = null;
        guiMap.remove((Player) e.getPlayer());
    }

    public void forceCloseGUI(Player p){
        p.closeInventory();
        slotMap = null;
        guiMap.remove((p));
    }
}
