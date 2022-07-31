package project.rpg.items.weapon

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import project.rpg.items.Items
import project.rpg.skill.electricity.Lightning

object Wand : WeaponBase() {

    override fun createItem() {
        val item = ItemStack(Material.STICK, 1)
        val meta = item.itemMeta
        skill = Lightning()

        //display name
        meta.displayName(text("전설의 막대기").color(TextColor.color(0xff55ff)))
        //lore
        meta.lore(ArrayList(listOf(text("이 막대기는 아주 위대하고"), text("전설적인 나무젓가락 입니다"))) as List<Component>?)
        //enchantments
        meta.addEnchant(Enchantment.LUCK, 1, true)
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        //아이템 아이디 설정
        meta.setCustomModelData(Items.WAND.value)
        item.itemMeta = meta
        super.item = item
    }

    override fun onEnable(action: Action, player : Player) {
        skill.onEnable(player, action)
    }
}