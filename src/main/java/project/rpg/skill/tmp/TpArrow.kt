package project.rpg.skill.tmp

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.title.Title
import org.bukkit.Bukkit
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.AbstractArrow
import org.bukkit.entity.Arrow
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import project.rpg.data.structures.ListQueue
import project.rpg.player.User.Companion.getPlayer
import project.rpg.skill.SkillType
import project.rpg.skill.magic.MagicSkillBase

class TpArrow : MagicSkillBase() {
    private val thrownArrows = ListQueue<Arrow>()
    override fun onEnable(player: Player, action: Action?) {
        if (action!!.isRightClick) {
            onRightClick(player)
        } else {
            onLeftClick(player)
        }
    }

    private fun onRightClick(player: Player) {
        if (coolTime == 0) {
            for (all in Bukkit.getOnlinePlayers()) {
                all.playSound(player.location, Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 0.6f, 2f)
            }
            val direction = player.location.direction
            val arrow = nextValidArrow
            if (arrow == null) {
                player.showTitle(
                    Title.title(
                        Component.text(" "),
                        Component.text("소환되어있는 화살이 없습니다!").color(TextColor.color(0xff5555))
                    )
                )
                getPlayer(player)!!.mana.addMana(needMana)
                return
            } else {
                arrow.remove()
                player.teleport(arrow.location.setDirection(direction))
                player.world.spawnParticle(Particle.GLOW, player.location, 100, 0.5, 1.0, 0.5)
            }
            coolTime = skillTime
        }
        player.sendMessage(Component.text("남은 화살 수 : ").append(Component.text(thrownArrows.size)))
    }

    fun onLeftClick(player: Player) {
        if (thrownArrows.size >= 10) {
            val poppedArrow = nextValidArrow
            if (poppedArrow != null) {
                for (all in Bukkit.getOnlinePlayers()) {
                    all.spawnParticle(Particle.CLOUD, poppedArrow.location, 10, 0.02, 0.02, 0.02, 0.1)
                }
                poppedArrow.remove()
            }
        }
        val arrow = player.launchProjectile(Arrow::class.java)
        arrow.knockbackStrength = 3
        arrow.setGravity(true)
        arrow.pickupStatus = AbstractArrow.PickupStatus.DISALLOWED
        arrow.velocity = player.location.direction.multiply(5)
        thrownArrows.add(arrow)
        player.sendMessage(Component.text("남은 화살 수 : ").append(Component.text(thrownArrows.size)))
    }

    private val nextValidArrow: Arrow?
        private get() {
            if (thrownArrows.isEmpty) return null
            var arrow = thrownArrows.pop()
            while (!arrow.isValid) {
                arrow = thrownArrows.pop()
            }
            return arrow
        }

    init {
        name = SkillType.TP_ARROW.skillName
        description = "특수한 술식으로 마킹된 공간으로 술자를 역소환하는 술법"
        needMana = 5
        circle = 6
    }
}