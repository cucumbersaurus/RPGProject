package project.rpg.items.weapon

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import project.rpg.annotation.skill
import project.rpg.effect.Burns
import project.rpg.items.Items
import project.rpg.player.User
import project.rpg.textComponets.color.TextColors

object BlazingMark : WeaponBase() {

    override fun createItem() {
        val item = ItemStack(Material.BLAZE_ROD)
        val meta = item.itemMeta

        meta.displayName(Component.text("타오르는 표시").color(TextColors.CRIMSON.color))
        meta.lore(itemLore())
        meta.setCustomModelData(Items.BLAZING_MARK.value)

        item.itemMeta = meta
        super.item = item
    }

    @skill(name = "blazing_mark")
    override fun onEnable(action: Action, player: Player) {
        val mana = User.getPlayer(player).mana

        if (mana.useMana(10)) {
            val entity = player!!.getTargetEntity(10, false)
            if (entity != null && entity is LivingEntity) {
                Burns(entity, 8)
            }
            if (entity is Player) {
                entity.addPotionEffect(PotionEffect(PotionEffectType.WEAKNESS, 20 * 7, 2, true))
            }

            for (p in player.location.getNearbyPlayers(7.0, 7.0, 7.0)) {
                p.playSound(player.location, Sound.BLOCK_BLASTFURNACE_FIRE_CRACKLE, 0.6f, 1f)
            }
        }
        player.world.spawnParticle(Particle.FLAME, player.location, 100, 0.25, 3.0, 0.25, 0.1)
    }

    private fun itemLore():List<Component> {
        val lore = ArrayList<Component>()
        lore.add(Component.text("앗 뜨거ㅓㅓ"))
        return lore
    }
}