package project.rpg.commands.debug

import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import project.rpg.mob.ZombieKing


class MobTest : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player) {
            if (args != null) {
                when (args.size) {
                    0 -> ZombieKing.spawn(sender.world, sender.location)
                    1->{
                        val t = args[0].toInt()
                        for (i in 1..t){
                            ZombieKing.spawn(sender.world, sender.location)
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