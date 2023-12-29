package project.rpg.player.name.title

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import project.rpg.items.ItemBase
import project.rpg.items.Items
import project.rpg.player.name.base.TitleBase
import project.rpg.player.name.base.TitleName
import java.util.*

class MvpPP(player: Player?) :
    TitleBase(player!!, TitleName.MVPPP.title, TitleName.MVPPP.description, TitleName.MVPPP.acquisitionConditions) {
    //MVP++   https://www.notion.so/MVP-cc9e7ea1f98742f1a2a0ae065e233466
    override fun onEnable() {
        player.isGlowing = true //발광 추가 팀색깔로 발광되던데 팀 추가는 나중에
    }

    override fun onDisable() {
        player.isGlowing = false //꺼짐
    }

    override fun eared() {
        player.inventory.addItem(GoldenTrophy.instance.item!!) //황금 트로비 지급
    }
}

internal class GoldenTrophy private constructor() : ItemBase() {
    override fun createItem() {
        val item = ItemStack(Material.GOLD_BLOCK, 1)
        val meta = item.itemMeta

        //display name
        meta.displayName(Component.text("HIGHPIXEL 트로피").color(TextColor.color(0xfaf337)))
        //lore
        meta.lore(ArrayList<Component>(Arrays.asList(Component.text("어디서 많이 본 것 같은데"), Component.text("뭐 기분 탓이겠지"))))
        //enchantments
        meta.addEnchant(Enchantment.LOYALTY, 1, true)
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        //아이템 아이디 설정
        meta.setCustomModelData(Items.WAND.value)
        item.itemMeta = meta
        super.item = item
    }

    companion object {
        //싱글톤으로
        val instance = GoldenTrophy()
    }
}