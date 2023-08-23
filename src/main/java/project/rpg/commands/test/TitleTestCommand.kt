package project.rpg.commands.test

import net.kyori.adventure.text.Component.text
import net.kyori.adventure.title.Title
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.time.Duration.ofSeconds

class TitleTestCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player && args.isNotEmpty()) {
            sender.showTitle(
                Title.title(
                    text(args[0]),
                    text(args[0]),
                    Title.Times.times(ofSeconds(20), ofSeconds(60), ofSeconds(20))
                )
            )
        }
        return false
    }
}