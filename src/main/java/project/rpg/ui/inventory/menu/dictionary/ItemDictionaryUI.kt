package project.rpg.ui.inventory.menu.dictionary

import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Material
import org.bukkit.OfflinePlayer
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import project.rpg.items.Items
import project.rpg.textComponets.color.DefaultTextColors
import project.rpg.ui.inventory.GuiBase
import java.net.URL

class ItemDictionaryUI(player: Player) : GuiBase(player, 54, text("아이템 도감")) {

    private var currentPage = 0
    private val totalPages = Items.values().size/28

    override fun initialize(player: Player) {

        setFrame()
        setItem(text("닫기"), null, Material.BARRIER, 1, 49, Button.CLOSE.name, false)

        val from = currentPage*28
        val to = if(currentPage*28 + 27>=Items.values().size) Items.values().size-1 else currentPage*28+27
        for(i in from..to) {
            val pagePos = i-currentPage*28
            val slot = 9*(pagePos/7)+pagePos%7+10
            if(i==0){
                setItem(text("NULL").color(TextColor.color(DefaultTextColors.RED.color)), null, Material.BARRIER, 1, slot, Button.ITEM.name, false)
            }
            else setItem(Items.values()[i].item?.clone(), slot, Button.ITEM.name)
        }

        when (currentPage) {
            0 -> {
                makeNextButton(player)
            }
            totalPages -> {
                makePeriviousButton(player)
            }
            else -> {
                makePeriviousButton(player)
                makeNextButton(player)
            }
        }

    }

    private fun makePeriviousButton(player:OfflinePlayer) {
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

        setItem(leftArrow, 45, Button.PERIVIOUS.name)
    }

    private fun makeNextButton(player: OfflinePlayer) {
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

        setItem(rightArrow, 53, Button.NEXT.name)
    }

    private fun reloadUI(player: Player){
        for(i in 0..27){
            val slot = 9*(i/7)+i%7+10
            removeItem(slot)
        }
        initialize(player)
    }

    override fun onClick(event: InventoryClickEvent) {
        event.isCancelled = true
        val player = event.whoClicked as Player
        when (getValue(event.slot)) {
            Button.BACKGROUND.name -> return
            Button.ITEM.name ->{
                val id = slotToItem(event.slot)
                if(id<Items.values().size) {
                    val item = Items.values()[id].item
                    if (item != null && player.isOp) {
                        player.inventory.addItem(item)
                        player.playSound(player.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1f)
                    }
                }
            }
            Button.CLOSE.name ->{
                forceCloseGUI(player)
            }
            Button.NEXT.name->{
                currentPage+=1
                reloadUI(player)
            }
            Button.PERIVIOUS.name->{
                currentPage-=1
                reloadUI(player)
            }
        }
    }

    private fun setFrame(){
        for (i in 0..8){
            setItem(text(" "), null, Material.WHITE_STAINED_GLASS_PANE, 1, i, Button.BACKGROUND.name, false)
        }
        for (i in 9..53 step 9){
            setItem(text(" "), null, Material.WHITE_STAINED_GLASS_PANE, 1, i, Button.BACKGROUND.name, false)
        }
        for (i in 17..53 step 9){
            setItem(text(" "), null, Material.WHITE_STAINED_GLASS_PANE, 1, i, Button.BACKGROUND.name, false)
        }
        for (i in 46..53){
            setItem(text(" "), null, Material.WHITE_STAINED_GLASS_PANE, 1, i, Button.BACKGROUND.name, false)
        }
    }

    private fun slotToItem(slot: Int):Int{
        return (slot-8)-2*(slot/9) + currentPage*28
    }

    private enum class Button {
        BACKGROUND,
        ITEM,
        CLOSE,
        NEXT,
        PERIVIOUS,
    }

}