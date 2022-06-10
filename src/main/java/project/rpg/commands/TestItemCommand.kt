package project.rpg.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import project.rpg.items.ManaRefillPotion
import project.rpg.items.Wand

class TestItemCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (args.isNotEmpty()) {
                if (args[0] == "wand") {
                    sender.inventory.addItem(Wand.getItem().clone())
                } else if (args[0] == "mana_refill_potion") {
                    sender.inventory.addItem(ManaRefillPotion.getItem().clone())
                }
            }
        }
        return false
    }
}