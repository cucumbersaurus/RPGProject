package project.rpg.items.potion

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.text
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.PotionMeta
import project.rpg.Rpg
import project.rpg.items.Items
import project.rpg.player.mana.Mana

object ManaRefillPotion : PotionBase() {
    override fun createItem() {
        val item = ItemStack(Material.POTION, 1)
        val meta = item.itemMeta as PotionMeta

        //display name
        meta.displayName(text("마나 회복 포션"))
        //lore
        meta.lore(ArrayList(listOf(text("이 포션을 마시면"), text("최대 100의 마나를 회복합니다"))) as List<Component>?)
        //아이템 아이디 설정
        meta.setCustomModelData(Items.MANA_REFILLING_POTION.value)

        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS)
        meta.color = Color.AQUA

        item.itemMeta = meta
        super.item = item
    }

    override fun onDrink(mana: Mana, plugin: Rpg, event: PlayerItemConsumeEvent) {
        val player: Player = event.player
        val item = event.item

        if (item.itemMeta.customModelData == Items.MANA_REFILLING_POTION.value) {
            val leftUntilFull = mana.maxMana - mana.mana
            if (leftUntilFull >= 100) {
                mana.addMana(100)
            } else {
                mana.addMana(mana.maxMana - mana.mana)
            }
            plugin.actionBar.updateActionBar(player)
        }
    }
}