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
    protected val inventory : Inventory
    protected val player : Player
    @Deprecated("키 대신 람다 넣는게 효율적인듯")
    private val slotMap : HashMap<Int, String?> //인벤토리 슬롯당 태그 할당
    private val slotFuncMap: HashMap<Int, ((InventoryClickEvent, Int)->Unit)?>



    init {
        this.player = player
        inventory = Bukkit.createInventory(null, guiSize, guiName!!) //새 인벤토리
        slotMap = HashMap() //새 슬롯맵
        slotFuncMap = HashMap()
        this.initialize(player) //상속받은 클래스에서 구현
        this.also { guiMap_[player] = it } //맵에 저장
        player.openInventory(inventory) //인벤토리 보여주기
    }

    /**
     * @param player 이 플레이어에게 보여줄 인벤토리(상자)를 초기화
     */
    protected abstract fun initialize(player: Player)

    @Deprecated("slotMap 더이상 사용되지 않음")
    /**
     * @param event 인벤토리 클릭 이벤트
     * 이벤트 발생시 특정 아이템에 대한 기능 실행 구현(필수 아님)
     */
    open fun onClick(event: InventoryClickEvent){}

    fun onClickEvent(event: InventoryClickEvent){
        val slot = event.slot
        executeSlotFunc(event, slot)
    }

    fun setFunc(slot: Int, func: ((InventoryClickEvent, Int)->Unit)){
        slotFuncMap[slot] = func
    }

    fun fillBackGround(material: Material = Material.WHITE_STAINED_GLASS_PANE){
        for(i in 1..53){
            setItem(material, i)
        }
    }

    @Deprecated("slotMap 더이상 사용되지 않음")
    /**
     * @param name 아이템의 표기 이름 (Component)
     * @param lore 아이템의 부연설명 (ArrayList< Component >)
     * @param material 아이템의 종류
     * @param amount 아이템의 양
     * @param slot 아이템을 보여줄 위치
     * @param value 아이템을 클릭했을때의 식별자 (onClick() 에서 분기문 작성시 식별자)
     * @param isGlow 아이템에 인첸트 효과를 추가할지
     *
     * @see setItem
     */
    protected fun setItem(
        name: Component? = null,
        lore: ArrayList<Component?>? = null,
        material: Material = Material.AIR,
        amount: Int=1,
        slot: Int = 0,
        value: String, //슬롯맵에 저장할 태그
        isGlow: Boolean = false
    ) { //특정 슬롯에 특정 아이템 설정
        val item = ItemStack(material, amount).apply {
            itemMeta = itemMeta.apply{
                displayName(name)
                lore(lore)
                if(isGlow) {
                    addEnchant(Enchantment.LURE, 0, true)
                    addItemFlags(ItemFlag.HIDE_ENCHANTS)
                }
            }
        }
        slotMap[slot] = value
        inventory.setItem(slot, item)
    }


    /**
     * @param name 아이템의 표기 이름 (Component)
     * @param lore 아이템의 부연설명 (ArrayList< Component >)
     * @param material 아이템의 종류
     * @param amount 아이템의 양
     * @param slot 아이템을 보여줄 위치
     * @param func 아이템을 클릭했을때의 실행될 람다식 (없으면 null)
     * @param isGlow 아이템에 인첸트 효과를 추가할지
     */
    protected fun setItem(
        material: Material,
        slot: Int,
        func: ((InventoryClickEvent, Int)->Unit)? = null, //슬롯을 클릭했을때 실행되는 람다
        name: Component? = text(" "),
        lore: List<TextComponent>? = null,
        amount: Int=1,
        isGlow: Boolean = false
    ) { //특정 슬롯에 특정 아이템 설정
        val item = ItemStack(material, amount).apply {
            itemMeta = itemMeta.apply{
                displayName(name)
                lore(lore)
                if(isGlow) {
                    addEnchant(Enchantment.LURE, 0, true)
                    addItemFlags(ItemFlag.HIDE_ENCHANTS)
                }
            }
        }
        slotFuncMap[slot] = func
        inventory.setItem(slot, item)
    }

    @Deprecated("slotMap 더이상 사용되지 않음")
    /**
     * @param item 메타데이터가 설정된 아이템
     * @param slot 아이템을 보여줄 위치
     * @param value 아이템을 클릭했을때의 식별자 (onClick() 에서 분기문 작성시 식별자)
     *
     * @see setItem
     */
    protected fun setItem(item: ItemStack?, slot: Int, value: String) {
        slotMap[slot] = value
        inventory.setItem(slot, item)
    }

    /**
     * @param item 메타데이터가 설정된 아이템
     * @param slot 아이템을 보여줄 위치
     * @param func 아이템을 클릭했을때 실행될 람다식 (없으면 null)
     */
    protected fun setItem(item: ItemStack?, slot: Int, func: ((InventoryClickEvent, Int) -> Unit)?) {
        slotFuncMap[slot] = func
        inventory.setItem(slot, item)
    }

    /**
     *
     * @param slot 아이템을 삭제할 슬롯
     */
    protected fun removeItem(slot: Int) {
        inventory.clear(slot)
    }

    @Deprecated("slotMap 더이상 사용되지 않음")
    /**
     * @param slot 인벤토리(상자)에서 아이템의 위치
     * @return 그 아이템의 식별자
     * @see executeSlotFunc
     */
    protected fun getValue(slot: Int): String? {
        return slotMap.getOrDefault(slot, null)
    } //인벤토리 슬롯의 태그 반환

    /**
     * 슬롯에 해당하는 람다식 실행
     * @param slot 슬롯
     */
    protected fun executeSlotFunc(event:InventoryClickEvent, slot: Int){
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
    fun resetGUI(){
        slotMap.clear()
        slotFuncMap.clear()
        for(i in 0..27){
            val slot = 9*(i/7)+i%7+10
            removeItem(slot)
        }
    }

    companion object {
        protected val guiMap_: HashMap<Player, GuiBase?> = HashMap() //누가 어떤 창을 보고 있는지 저장
        fun getGUI(player: Player): GuiBase? {
            return guiMap_[player]
        }
    }
}
