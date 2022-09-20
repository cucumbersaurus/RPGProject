package project.rpg.items.disposable

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import project.rpg.effect.Damage
import project.rpg.extensions.mana
import project.rpg.items.Items
import project.rpg.textComponets.color.TextColors

object ManaStone : DisposableBase() {
    override fun createItem() {
        val item = ItemStack(Material.DIAMOND)
        val meta = item.itemMeta

        meta.displayName(Component.text("마나 스톤").color(TextColors.MEDIUM_SLATE_BLUE.color))
        meta.lore(itemLore())
        meta.setCustomModelData(Items.MANA_STONE.value)
        meta.addEnchant(Enchantment.LURE, 1, true)
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS)

        item.itemMeta = meta
        super.item = item
    }

    override fun onUse(player: Player) {
        val mana = player.mana
        Damage(player,60)

        val leftUntilFull = mana.maxMana - mana.mana
        if (leftUntilFull >= 70) {
            mana.addMana(70)
        } else {
            mana.addMana(mana.maxMana - mana.mana)
        }
    }

    private fun itemLore():List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("피가 빠져 나간다..."))
        lore.add(Component.text("체력 60을 마나 30으로 바꾼다."))
        return lore
    }
}