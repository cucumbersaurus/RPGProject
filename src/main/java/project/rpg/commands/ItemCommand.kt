package project.rpg.commands

import net.kyori.adventure.text.Component.text
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import project.rpg.items.ItemDictionary
import project.rpg.items.Items
import project.rpg.textComponets.color.DefaultTextColors

class ItemCommand : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player && args.isNotEmpty()) {
            val item: ItemStack? = ItemDictionary.getNewItem(args[0])
            if(item !=null) sender.inventory.addItem()
            else sender.sendMessage(text("없는 아이템 입니다. 명령어를 확인해 주세요.").color(DefaultTextColors.RED.color))
        }
        return false
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>?
    ): MutableList<String> {
        val recommendation = ArrayList<String>()

        if(args != null && args.size==1) {
            for (item in Items.values()) {
                recommendation.add(item.name)
            }
        }
        return recommendation
    }
}