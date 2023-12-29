package project.rpg.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import project.rpg.player.User.Companion.getPlayer

class LevelCommand : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (args.size == 2) {
                when (args[0]) {
                    "add" -> getPlayer(sender)!!.levels.addExp(args[1].toLong())
                    "remove" -> getPlayer(sender)!!.levels.addExp(-args[1].toLong())
                    else -> {}
                }
            }
        }
        return false
    }

    override fun onTabComplete(
        p0: CommandSender,
        p1: Command,
        p2: String,
        p3: Array<out String>?
    ): List<String> {
        return emptyList()
        TODO("Not yet implemented")
    }
}
