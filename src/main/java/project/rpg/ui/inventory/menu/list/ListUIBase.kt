package project.rpg.ui.inventory.menu.list

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.text
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import project.rpg.ui.inventory.GuiBase
import java.net.URL

abstract class ListUIBase<T>(player: Player, guiName: Component, sourceList: Array<T>) : GuiBase(player,  guiName) {

    private var currentPage = 0
    private val totalPages = sourceList.size/28
    private val list = sourceList
    private val listSize = sourceList.size

    /**
     * 자동으로 표시 영역(7*4)에 아이템 채워넣기
     */
    protected fun setItems(){

        val from = currentPage*28
        val to = if(currentPage*28 + 27>=listSize) listSize-1 else currentPage*28+27

        for(i in from..to) {
            val pagePos = i - currentPage * 28
            val slot = 9 * (pagePos / 7) + pagePos % 7 + 10

            val item = convertToItemStack(list[i])
            setItem(item, slot, Button.ITEM.name)
        }

        when (currentPage) {
            0 -> makeNextButton()
            totalPages -> makePeriviousButton()
            else -> {
                makePeriviousButton()
                makeNextButton()
            }
        }

    }

    /**
     * --*구현 필수*--
     * @param source 리스트에 들어있는 요소
     * @return 리스트에 든 요소를 인벤토리에 표현 가능한 아이템으로 변환 후 리턴
     */
    abstract fun convertToItemStack(source:T):ItemStack

    private val previousButton:ItemStack
        get() {
            val leftArrow = ItemStack(Material.PLAYER_HEAD)
            val skullMeta = leftArrow.itemMeta as SkullMeta
            skullMeta.owningPlayer = player//profile이 null이 되는것을 방지하기 위해 플레이어로 더미 프로필을 생성(PlayerProfile이 interface라 인스턴스화 불가능해서 이렇게 함)
            val profile = skullMeta.playerProfile
            val texture = profile!!.textures
            texture.skin = URL("https://textures.minecraft.net/texture/b3295a9f11478e6b789bf6eb06296e016c127d0a627f354fec26c27f46d417")//가문비나무 왼쪽 화살표 텍스쳐 링크
            profile.setTextures(texture)
            skullMeta.playerProfile = profile

            skullMeta.displayName(text("이전 페이지로"))
            leftArrow.itemMeta = skullMeta
            return leftArrow
        }

    private val nextButton:ItemStack
        get() {
            val rightArrow = ItemStack(Material.PLAYER_HEAD)
            val skullMeta = rightArrow.itemMeta as SkullMeta
            skullMeta.owningPlayer = player//profile이 null이 되는것을 방지하기 위해 플레이어로 더미 프로필을 생성(PlayerProfile이 interface라 인스턴스화 불가능해서 이렇게 함)
            val profile = skullMeta.playerProfile
            val texture = profile!!.textures
            texture.skin = URL("https://textures.minecraft.net/texture/6bb0c63d5afc325856625b855ff2461f1560b3d1a74a1e4619e624069c5b962")//가문비나무 오른쪽 화살표 텍스쳐 링크
            profile.setTextures(texture)
            skullMeta.playerProfile = profile

            skullMeta.displayName(text("다음 페이지로"))
            rightArrow.itemMeta = skullMeta
            return rightArrow
        }


    private fun makePeriviousButton() {
        setItem(previousButton, 45, Button.PREVIOUS.name)
    }

    /**
     * 다음 버튼 생성
     */
    private fun makeNextButton() {
        setItem(nextButton, 53, Button.NEXT.name)
    }

    /**
     * @param material 해당 아이템으로 인벤토리에 테두리를 생성
     */
    protected fun setFrame(material: Material = Material.WHITE_STAINED_GLASS_PANE){
        for (i in 0..8){
            setItem(text(" "), null, material, 1, i, Button.BACKGROUND.name, false)
        }
        for (i in 9..53 step 9){
            setItem(text(" "), null, material, 1, i, Button.BACKGROUND.name, false)
        }
        for (i in 17..53 step 9){
            setItem(text(" "), null, material, 1, i, Button.BACKGROUND.name, false)
        }
        for (i in 46..53){
            setItem(text(" "), null, material, 1, i, Button.BACKGROUND.name, false)
        }
        setItem(text("닫기"), null, Material.BARRIER, 1, 49, Button.CLOSE.name, false)
    }

    /**
     * UI를 새로고침
     * 모든 아이템을 삭제 후 initialize를 다시 진행
     */
    protected fun reloadUI(){
        resetGUI()
        initialize(player)
    }

    /**
     * @param slot 클릭되거나 리스트의 인덱스를 알고 싶은 슬롯
     * @return slot에 해당하는 리스트의 인덱스
     */
    protected fun slotToIndex(slot: Int): Int{
        return (slot-8)-2*(slot/9) + currentPage*28
    }

    private enum class Button{
        BACKGROUND,
        PREVIOUS,
        NEXT,
        ITEM,
        CLOSE
    }

}