package project.rpg.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import project.rpg.items.ManaRefillPotion
import project.rpg.items.Wand

class TestItemCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player && args.isNotEmpty()) {
            if (args[0] == "wand") {
                sender.inventory.addItem(Wand.item!!.clone())
            } else if (args[0] == "mana_refilling_potion") {
                sender.inventory.addItem(ManaRefillPotion.item!!.clone())
            }
        }
        return false
    }
}