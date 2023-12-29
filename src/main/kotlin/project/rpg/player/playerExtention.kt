package project.rpg.player

import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player

val Player.title
    get() = User.getPlayer(this)?.title!!

val Player.status
    get() = User.getPlayer(this)?.status!!

val Player.mana
    get() = User.getPlayer(this)?.mana!!

val Player.levels
    get() = User.getPlayer(this)?.levels!!

val Player.job
    get() = User.getPlayer(this)?.job!!

val Player.friends
    get() = User.getPlayer(this)?.friends!!

fun Player.setAttributeMaxHealth(health: Double) {
    this.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.baseValue = health
}