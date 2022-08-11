package project.rpg.items.potion

import net.kyori.adventure.text.Component
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import project.rpg.Rpg
import project.rpg.items.Items
import project.rpg.player.User
import project.rpg.player.mana.Mana

object TearOfDragon : PotionBase() {
    override fun createItem() {
        val item = ItemStack(Material.POTION, 1)
        val meta = item.itemMeta as PotionMeta

        //display name
        meta.displayName(Component.text("용의 눈물"))
        //lore
        meta.lore(ArrayList(listOf(Component.text("이 포션을 마시면"), Component.text("공격력 200% 증가, 체력 초당 50 회복 효과를 10초간 부여 받습니다"))) as List<Component>?)
        //아이템 아이디 설정
        meta.setCustomModelData(Items.TEAR_OF_DRAGON.value)

        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS)
        meta.color = Color.fromRGB(0xa8d1cc)

        item.itemMeta = meta
        super.item = item
    }

    override fun onDrink(mana : Mana, plugin : Rpg, event : PlayerItemConsumeEvent) {
        val player: Player = event.player
        val mana = User.getPlayer(player).mana
        val item = event.item

        if (item.itemMeta.customModelData== Items.TEAR_OF_DRAGON.value) {
            //TODO : 공격력 200% 증가
            val leftUntilFull = mana.maxMana - mana.mana
            if (leftUntilFull >= 500) {
                mana.addMana(500)
            } else {
                mana.addMana(mana.maxMana - mana.mana)
            }
            plugin.actionBar.updateActionBar(player)
            player.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION,20*20,10,true,true,true,))
        }
    }
}