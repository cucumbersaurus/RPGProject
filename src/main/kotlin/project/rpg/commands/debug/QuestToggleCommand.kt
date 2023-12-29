package project.rpg.commands.debug

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import project.rpg.ui.text.ScoreboardUI

class QuestToggleCommand : CommandExecutor, TabCompleter {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player) {

            val scoreboardUI: ScoreboardUI =
                if (ScoreboardUI.getPlayer(sender) != null) ScoreboardUI.getPlayer(sender)!!
                else ScoreboardUI(sender)

            if (args.isNotEmpty()) {
                when (args[0]) {
                    "true" -> {
                        if (args.size > 2) scoreboardUI.setContents(args.toList().subList(1, args.size))
                        scoreboardUI.showScoreboard()
                    }

                    "false" -> scoreboardUI.hideScoreboard()
                    else -> sender.sendMessage(command.usage)
                }
            }
        }
        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<String>
    ): List<String>? {
        if (args.size == 1) {
            val recommendation: MutableList<String> = ArrayList()
            recommendation.add("true")
            recommendation.add("false")
            return recommendation
        }
        return null
    }
}