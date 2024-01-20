package project.rpg.commands.debug

import io.github.monun.kommand.KommandArgument.Companion.string
import io.github.monun.kommand.PluginKommand
import io.github.monun.kommand.getValue
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer
import org.bukkit.entity.Player
import project.rpg.mob.MobType
import project.rpg.mob.SnowMonster
import project.rpg.mob.ZombieKing


class MobTest : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is CraftPlayer) {
            if (args != null) {
                when (args.size) {
                    0 -> ZombieKing.spawn(sender.world, sender.location)
                    1-> {
                        if (args[0] == "snow") {
                            SnowMonster(sender.location)
                        } else {
                            val t = args[0].toInt()
                            for (i in 1..t) {
                                ZombieKing.spawn(sender.world, sender.location)
                            }
                        }
                    }
                    3 -> ZombieKing.spawn(sender.world, Location(sender.world, args[0].toDouble(), args[1].toDouble(), args[2].toDouble()))
                }
            } else {
                ZombieKing.spawn(sender.world, sender.location)
                return true
            }
            return true
        }
        return false
    }
}

val autoCompleteMob = string().apply {
    suggests {
        val list = ArrayList<String>()
        for (mob in MobType.entries) {
            list.add(mob.name.lowercase())
        }
        suggest(list)
    }
}


fun PluginKommand.registerMobCommand() {
    register("mob") {
        requires { isPlayer && isOp }
        then("mobType" to autoCompleteMob) {
            executes {
                val player = sender as Player
                val mobType: String by it
                val mob = MobType.valueOf(mobType.uppercase())
                mob.instance.spawn(player.world, player.location)
            }
            then("spawnCount" to int()) {
                executes {
                    val spawnCount: Int by it
                    val player = sender as Player
                    val mobType: String by it
                    val mob = MobType.valueOf(mobType.uppercase())

                    for (i in 1..spawnCount) {
                        mob.instance.spawn(player.world, player.location.add(0.0, 0.0001 * i, 0.0))
                    }
                }
            }
        }
    }
}