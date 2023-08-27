package project.rpg.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import project.rpg.extensions.status

class StatusCommand : CommandExecutor, TabCompleter {

    private lateinit var arg1: String
    private lateinit var arg2: String
    private lateinit var player: Player

    private val recommendations = listOf(
        listOf("add"),
        listOf("strength", "agility", "speed", "health", "defense", "luck", "handicraft", "intelligence")
    )

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {

        if (sender is Player) {
            player = sender
            val status = player.status

            if ("add" == args[0]) {
                if (args.size <= 2) {
                    sender.sendMessage(command.usage)
                    return true
                }
                this.arg1 = args[1]
                this.arg2 = args[2]

                val num = arg2.toInt()
                if (status.addStatus(arg1, num)) sendFeedback(true)
//                status.addStatus(args[1], num)
//                when (args[1]) {
//                    "strength" -> {
//                        sendFeedback(true)
//                        status.addStatus(StatusName.STRENGTH, num)
//                    }
//
//                    "agility" -> {
//                        sendFeedback(true)
//                        status.addStatus(StatusName.AGILITY, num)
//                    }
//
//                    "speed" -> {
//                        sendFeedback(true)
//                        status.addStatus(StatusName.SPEED, num)
//                    }
//
//                    "health" -> {
//                        sendFeedback(true)
//                        status.addStatus(StatusName.HEALTH, num)
//                    }
//
//                    "defense" -> {
//                        sendFeedback(true)
//                        status.addStatus(StatusName.DEFENSE, num)
//                    }
//
//                    "luck" -> {
//                        sendFeedback(true)
//                        status.addStatus(StatusName.LUCK, num)
//                    }
//
//                    "handicraft" -> {
//                        sendFeedback(true)
//                        status.addStatus(StatusName.HANDICRAFT, num)
//                    }
//
//                    "intelligence" -> {
//                        sendFeedback(true)
//                        status.addStatus(StatusName.INTELLIGENCE, num)
//                    }
//
//                    else -> sendFeedback(false)
//                }
                status.reloadMap()
            } else sendFeedback(false)
            return true
        }
        return false
    }

    private fun sendFeedback(isSuccess: Boolean) {
        if (isSuccess) player.sendMessage("$arg1 is added $arg2")
        else player.sendMessage("오타난 커맨드")
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>?
    ): List<String> {
        val recommendation = ArrayList<String>()

        if (args != null) {
            when (args.size) {
                1 -> recommendations[0]
                2 -> recommendations[1]
            }
        }
        return recommendation
    }
}