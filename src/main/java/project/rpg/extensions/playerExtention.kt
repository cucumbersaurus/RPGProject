package project.rpg.extensions

import org.bukkit.entity.Player
import project.rpg.player.User

val Player.title
    get() = User.getPlayer(this).title!!

val Player.status
    get() = User.getPlayer(this).status!!

val Player.mana
    get() = User.getPlayer(this).mana!!

val Player.levels
    get() = User.getPlayer(this).levels!!

val Player.job
    get() = User.getPlayer(this).job!!

val Player.skill
    get() = User.getPlayer(this).skill!!

val Player.friends
    get() = User.getPlayer(this).friends!!