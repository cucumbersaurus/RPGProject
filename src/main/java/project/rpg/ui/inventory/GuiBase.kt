package project.rpg.ui.inventory

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import project.rpg.extensions.setDisplayName
import project.rpg.extensions.setGlow

abstract class GuiBase protected constructor(player: Player, guiSize: Int, guiName: Component?) {
    protected val inventory : Inventory //그냥 인벤토리
    private var slotMap : MutableMap<Int, String?>? //인벤토리 슬롯당 태그 할당


    init {
        inventory = Bukkit.createInventory(null, guiSize, guiName!!) //새 인벤토리
        slotMap = HashMap() //새 슬롯맵
        this.initialize(player) //상속받은 클래스에서 구현
        this.also { guiMap_[player] = it } //맵에 저장
        player.openInventory(inventory) //인벤토리 보여주기
    }

    /**
     * @param player 이 플레이어에게 보여줄 인벤토리(상자)를 초기화
     */
    protected abstract fun initialize(player: Player)

    /**
     * @param event 인벤토리 클릭 이벤트
     * 이벤트 발생시 특정 아이템에 대한 기능 실행 구현(필수 아님)
     */
    abstract fun onClick(event: InventoryClickEvent)

    /**
     * @param name 아이템의 표기 이름 (Component)
     * @param lore 아이템의 부연설명 (ArrayList< Component >)
     * @param material 아이템의 종류
     * @param amount 아이템의 양
     * @param slot 아이템을 보여줄 위치
     * @param value 아이템을 클릭했을때의 식별자 (onClick() 에서 분기문 작성시 식별자)
     * @param isGlow 아이템에 인첸트 효과를 추가할지
     */
    protected fun setItem(
        name: Component? = null,
        lore: ArrayList<Component?>? = null,
        material: Material = Material.AIR,
        amount: Int=1,
        slot: Int = 0,
        value: String = " ", //슬롯맵에 저장할 태그
        isGlow: Boolean = false
    ) { //특정 슬롯에 특정 아이템 설정
        val item = ItemStack(material, amount).apply {
            setDisplayName(name)
            lore(lore)
            if(isGlow) setGlow()
        }
        slotMap!![slot] = value
        inventory.setItem(slot, item)
    }

    /**
     * @param item 메타데이터가 설정된 아이템
     * @param slot 아이템을 보여줄 위치
     * @param value 아이템을 클릭했을때의 식별자 (onClick() 에서 분기문 작성시 식별자)
     */
    protected fun setItem(item: ItemStack?, slot: Int, value: String) {
        slotMap!![slot] = value
        inventory.setItem(slot, item)
    }

    /**
     *
     * @param slot 아이템을 삭제할 슬롯
     */
    protected fun removeItem(slot: Int) {
        inventory.clear(slot)
    }

    /**
     * @param slot 인벤토리(상자)에서 아이템의 위치
     * @return 그 아이템의 식별자
     */
    protected fun getValue(slot: Int): String? {
        return slotMap!!.getOrDefault(slot, null)
    } //인벤토리 슬롯의 태그 반환

    /**
     * @param event 인벤토리 닫기 이벤트
     */
    fun closeGUI(event: InventoryCloseEvent) { //인벤토리 닫힐 경우
        slotMap = null
        guiMap_.remove(event.player)
    }

    /**
     * @param player 강제로 닫을 인벤토리를 보고 있는 플레이어
     */
    fun forceCloseGUI(player: Player) { //강제로 닫기
        player.closeInventory()
        slotMap = null
        guiMap_.remove(player)
    }

    companion object {
        protected val guiMap_: MutableMap<Player, GuiBase?> = HashMap() //누가 어떤 창을 보고 있는지 저장
        fun getGUI(p: Player): GuiBase? {
            return guiMap_.getOrDefault(p, null)
        }
    }
}
