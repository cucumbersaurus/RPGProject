package project.rpg.commands

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import project.rpg.extensions.friends

class FriendCommand : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (args.size == 2) {
                when (args[0]) {
                    "add" -> sender.friends.requestFriend(args[1])
                    "accept" -> sender.friends.acceptFriend(args[1])
                    "list" -> sender.friends.printFriendsList()
                    else -> return false
                }
            } else if (args.size == 1 && args[0] == "list") {
                sender.friends.printFriendsList()
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
        val result: MutableList<String> = ArrayList()
        if (args.size == 1) {
            result.add("add")
            result.add("accept")
            result.add("list")
        }
        if (sender is Player && args.size == 2) {
            when(args[0]) {
                "add" -> {
                    for (player in Bukkit.getOnlinePlayers()) {
                        result.add(player.name)
                    }
                }
                "accept" -> {
                    for (player in sender.friends.pendingList) {
                        result.add(player.name)
                    }
                }
            }
        }
        return result
    }
}