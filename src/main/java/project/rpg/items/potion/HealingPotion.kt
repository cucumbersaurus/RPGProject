package project.rpg.items.potion

import net.kyori.adventure.text.Component
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.PotionMeta
import project.rpg.Rpg
import project.rpg.effect.objects.Heal
import project.rpg.items.Items
import project.rpg.player.mana.Mana

object HealingPotion : PotionBase() {
    override fun createItem() {
        val item = ItemStack(Material.POTION, 1)
        val meta = item.itemMeta as PotionMeta

        //display name
        meta.displayName(Component.text("체력 회복 포션"))
        //lore
        meta.lore(
            ArrayList(
                listOf(
                    Component.text("이 포션을 마시면"),
                    Component.text("최대 70의 체력를 회복합니다")
                )
            ) as List<Component>?
        )
        //아이템 아이디 설정
        meta.setCustomModelData(Items.HEALING_POTION.value)

        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS)
        meta.color = Color.RED

        item.itemMeta = meta
        super.item = item
    }

    override fun onDrink(mana: Mana, plugin: Rpg, event: PlayerItemConsumeEvent) {
        val player: Player = event.player
        val item = event.item

        if (item.itemMeta.customModelData == Items.HEALING_POTION.value) {
            Heal(player, 70)
            plugin.actionBar.updateActionBar(player)
        }
    }
}