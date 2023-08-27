package project.rpg.commands

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import project.rpg.extensions.friends

class FriendCommand : CommandExecutor, TabCompleter {

    private val recommendations = listOf(listOf("add", "accept", "list"))

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player) {
            val friends = sender.friends

            if (args.isNotEmpty() && args[0] == "list") {
                friends.printFriendsList()
                return true
            }
            if (args.size >= 2) {
                return when (args[0]) {
                    "add" -> friends.requestFriend(args[1])
                    "accept" -> friends.acceptFriend(args[1])
                    else -> false
                }
            }
        }
        return false
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<String>
    ): List<String> {
        if (args.size == 1) {
            return recommendations[0]
        }
        if (sender is Player && args.size == 2) {
            val result: MutableList<String> = ArrayList()
            when (args[0]) {
                "add" -> Bukkit.getOnlinePlayers().forEach { result.add(it.name) }
                "accept" -> sender.friends.pendingList.forEach { result.add(it.name) }
            }
            return result
        }
        return emptyList()
    }
}