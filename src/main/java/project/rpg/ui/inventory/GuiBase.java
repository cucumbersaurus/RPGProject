package project.rpg.ui.inventory;

import net.kyori.adventure.text.Component;
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
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class GuiBase {

    protected static final Map<Player,GuiBase> guiMap_ = new HashMap<>();//누가 어떤 창을 보고 있는지 저장
    protected Inventory _inventory;//그냥 인벤토리
    protected Map<Integer,String> _slotMap;//인벤토리 슬롯당 태그 할당

    public static GuiBase getGUI(Player p) { return guiMap_.getOrDefault(p, null); }

    /**
     * @param player 생성된 gui를 보여줄 플레이어
     * @param guiSize gui의 크기 (9의 배수가 아닐시 오류가 발생 할 수 있음)
     * @param guiName 생성된 인벤토리(상자) gui 상단에 표시될 이름
     */
    protected GuiBase(@NotNull Player player, int guiSize, Component guiName) {
        _inventory = Bukkit.createInventory(null, guiSize, guiName);//새 인벤토리
        _slotMap = new HashMap<>();//새 슬롯맵

        init(player);//상속받은 클래스에서 구현
        guiMap_.put(player, this);//맵에 저장
        player.openInventory(_inventory);//인벤토리 보여주기
    }

    /**
     * @param player 이 플레이어에게 보여줄 인벤토리(상자)를 초기화
     */
    protected abstract void init(@NotNull Player player);


    /**
     * @param event 인벤토리 클릭 이벤트
     *              이벤트 발생시 특정 아이템에 대한 기능 실행 구현(필수 아님)
     */
    public abstract void onClick(InventoryClickEvent event);

    /**
     * @param name 아이템의 표기 이름 (Component)
     * @param lore 아이템의 부연설명 (ArrayList< Component >)
     * @param material 아이템의 종류
     * @param amount 아이템의 양
     * @param slot 아이템을 보여줄 위치
     * @param value 아이템을 클릭했을때의 식별자 (onClick() 에서 분기문 작성시 식별자)
     * @param isGlow 아이템에 인첸트 효과를 추가할지
     */
    protected void setItem(Component name, @Nullable ArrayList<Component> lore, @NotNull Material material, int amount, int slot, @NotNull String value/*슬롯맵에 저장할 태그*/, boolean isGlow) {//특정 슬롯에 특정 아이템 설정
        ItemStack item = new ItemStack(material,amount);
        ItemMeta meta = item.getItemMeta();
        if(name != null) meta.displayName(name);
        if(lore != null) meta.lore(lore);
        if(isGlow) {
            meta.addEnchant(Enchantment.LURE, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        item.setItemMeta(meta);
        _slotMap.put(slot, value);
        _inventory.setItem(slot, item);
    }

    /**
     * @param item 메타데이터가 설정된 아이템
     * @param slot 아이템을 보여줄 위치
     * @param value 아이템을 클릭했을때의 식별자 (onClick() 에서 분기문 작성시 식별자)
     */
    protected void setItem(ItemStack item, int slot, String value){
        _slotMap.put(slot, value);
        _inventory.setItem(slot, item);
    }

    /**
     *
     * @param slot 아이템을 삭제할 슬롯
     */
    protected void removeItem(int slot){
        _inventory.clear(slot);
    }

    /**
     * @param slot 인벤토리(상자)에서 아이템의 위치
     * @return 그 아이템의 식별자
     */
    protected String getValue(int slot) {return _slotMap.getOrDefault(slot, null);}//인벤토리 슬롯의 태그 반환

    /**
     * @param event 인벤토리 닫기 이벤트
     */
    public void closeGUI(@NotNull InventoryCloseEvent event) {//인벤토리 닫힐 경우
        _slotMap = null;
        guiMap_.remove(event.getPlayer());
    }

    /**
     * @param player 강제로 닫을 인벤토리를 보고 있는 플레이어
     */
    public void forceCloseGUI(Player player){//강제로 닫기
        player.closeInventory();
        _slotMap = null;
        guiMap_.remove((player));
    }
}
