package project.rpg.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import project.rpg.items.objects.ManaRefillPotion
import project.rpg.items.objects.TheMeteor
import project.rpg.items.objects.Wand

class ItemCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player && args.isNotEmpty()) {
            if (args[0] == "wand") {
                sender.inventory.addItem(Wand.item!!.clone())
            } else if (args[0] == "mana_refilling_potion") {
                sender.inventory.addItem(ManaRefillPotion.item!!.clone())
            } else if (args[0] == "meteor") {
                sender.inventory.addItem(TheMeteor.item!!.clone())
            }
        }
        return false
    }
}