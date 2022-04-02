package project.rpg.base;

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

    protected static final Map<Player,GuiBase> guiMap_ = new HashMap<>();//누가 어떤 창을 보고 있는지 저장
    protected Inventory inv_;//그냥 인벤토리
    protected Map<Integer,String> slotMap_;//인벤토리 슬롯당 태그 할당

    public static GuiBase getGUI(Player p) { return guiMap_.getOrDefault(p, null); }

    protected GuiBase(@NotNull Player p, int guiSize, String guiName) {
        inv_ = Bukkit.createInventory(null, guiSize, guiName);//새 인벤토리
        slotMap_ = new HashMap<>();//새 슬롯맵
        init(p);//상속받은 클래스에서 구현
        p.openInventory(inv_);//인벤토리 보여주기
        guiMap_.put(p, this);//맵에 저장
    }

    protected abstract void init(@NotNull Player p);
    public abstract void onClick(InventoryClickEvent e);

    protected void setItem(String name, List<String> lore, @NotNull Material m, int amount, int slot, String value/*슬롯맵에 저장할 태그*/, boolean isGlow) {//특정 슬롯에 특정 아이템 설정
        ItemStack item = new ItemStack(m,amount);
        ItemMeta meta = item.getItemMeta();
        if(name != null) meta.setDisplayName(name);
        if(lore != null) meta.setLore(lore);
        if(isGlow) {
            meta.addEnchant(Enchantment.LURE, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        item.setItemMeta(meta);
        slotMap_.put(slot, value);
        inv_.setItem(slot, item);
    }
    protected void setItem(ItemStack item, int slot, String value){
        slotMap_.put(slot, value);
        inv_.setItem(slot, item);
    }

    protected String getValue(int slot) {return slotMap_.getOrDefault(slot, null);}//인벤토리 슬롯의 태그 반환

    public void closeGUI(@NotNull InventoryCloseEvent e) {//인벤토리 닫힐 경우
        slotMap_ = null;
        guiMap_.remove(e.getPlayer());
    }

    public void forceCloseGUI(Player p){//강제로 닫기
        p.closeInventory();
        slotMap_ = null;
        guiMap_.remove((p));
    }
}
