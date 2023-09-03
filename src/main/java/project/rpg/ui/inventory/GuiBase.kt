package project.rpg.ui.inventory

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.TextComponent
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

abstract class GuiBase protected constructor(player: Player, guiName: Component?, guiSize: Int = 54) {
    protected val inventory: Inventory
    protected val player: Player
    private val slotFuncMap: HashMap<Int, ((InventoryClickEvent, Int) -> Unit)?>
    private val size: Int


    init {
        this.player = player
        size = guiSize
        inventory = Bukkit.createInventory(null, guiSize, guiName!!) //새 인벤토리
        slotFuncMap = HashMap()
        this.initialize() //상속받은 클래스에서 구현
        this.also { guiMap_[player] = it } //맵에 저장
        player.openInventory(inventory) //인벤토리 보여주기
    }

    /**
     * 플레이어에게 보여줄 인벤토리(상자)를 초기화
     */
    protected abstract fun initialize()

    /**
     * InventoryClickEvent 에서 호출
     * @param event 발생한 InventoryClickEvent 그 자체
     */
    fun onClickEvent(event: InventoryClickEvent) {
        val slot = event.rawSlot
        executeSlotFunc(event, slot)
    }

    fun setFunc(slot: Int, func: ((InventoryClickEvent, Int) -> Unit)) {
        slotFuncMap[slot] = func
    }

    /**
     * @param material 인벤토리를 모두 채울 아이템 종류 기본값:흰색 유리판
     */
    fun fillBackGround(material: Material = Material.WHITE_STAINED_GLASS_PANE) {
        for (i in 0..53) {
            setItem(material, i)
        }
    }


    /**
     * @param material 아이템의 종류
     * @param slot 아이템을 보여줄 위치
     * @param func 아이템을 클릭했을때의 실행될 람다식 기본값 : { event, _ -> event.isCancelled = true }
     * @param name 아이템의 표기 이름 (Component) 기본값 : text(" ")
     * @param lore 아이템의 부연설명 (ArrayList< Component >) 기본값 : null
     * @param amount 아이템의 양 기본값 : 1
     * @param isGlow 아이템에 인첸트 효과를 추가할지 기본값 : false
     */
    protected fun setItem(
        material: Material,
        slot: Int,
        func: ((InventoryClickEvent, Int) -> Unit)? = { event, _ -> event.isCancelled = true }, //슬롯을 클릭했을때 실행되는 람다
        name: Component? = text(" "),
        lore: List<TextComponent>? = null,
        amount: Int = 1,
        isGlow: Boolean = false
    ) { //특정 슬롯에 특정 아이템 설정
        val item = ItemStack(material, amount).apply {
            itemMeta = itemMeta.apply {
                displayName(name)
                lore(lore)
                if (isGlow) {
                    addEnchant(Enchantment.LURE, 0, true)
                    addItemFlags(ItemFlag.HIDE_ENCHANTS)
                }
            }
        }
        slotFuncMap[slot] = func
        inventory.setItem(slot, item)
    }


    /**
     * @param item 메타데이터가 설정된 아이템
     * @param slot 아이템을 보여줄 위치
     * @param func 아이템을 클릭했을때 실행될 람다식 (없으면 null)
     */
    protected fun setItem(
        item: ItemStack?,
        slot: Int,
        func: ((InventoryClickEvent, Int) -> Unit)? = { event, _ -> event.isCancelled = true }
    ) {
        slotFuncMap[slot] = func
        inventory.setItem(slot, item)
    }

    /**
     *
     * @param slot 아이템을 삭제할 슬롯
     */
    private fun removeItem(slot: Int) {
        inventory.clear(slot)
    }

    /**
     * 슬롯에 해당하는 람다식 실행
     * @param slot 슬롯
     */
    private fun executeSlotFunc(event: InventoryClickEvent, slot: Int) {
        slotFuncMap[slot]?.invoke(event, slot)
    }

    /**
     * @param event 인벤토리 닫기 이벤트
     */
    fun closeGUI(event: InventoryCloseEvent) { //인벤토리 닫힐 경우
        guiMap_.remove(event.player)
    }

    /**
     * 인벤토리를 강제로 닫음
     */
    fun forceCloseGUI() { //강제로 닫기
        player.closeInventory()
        guiMap_.remove(player)
    }

    /**
     * 인벤토리 초기화
     */
    fun resetGUI() {
        slotFuncMap.clear()
        for (i in 0 until size - 1) {
            removeItem(i)
        }
    }

    companion object {
        protected val guiMap_: HashMap<Player, GuiBase> = HashMap() //누가 어떤 창을 보고 있는지 저장
        private fun getGUI(player: Player): GuiBase? {
            return guiMap_[player]
        }

        fun clickedBy(event:InventoryClickEvent) {
            val gui = getGUI(event.whoClicked as Player)
            gui?.onClickEvent(event)
        }

        fun closedBy(event:InventoryCloseEvent) {
            val gui = GuiBase.getGUI(event.player as Player)
            gui?.closeGUI(event)
        }

    }
}
