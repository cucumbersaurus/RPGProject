package project.rpg.commands.debug

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import project.rpg.items.deSerializeToItemStack
import project.rpg.items.serializeToJson

class SerializeCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if(sender is Player){
            if(args.isEmpty()) {
                val item = sender.inventory.itemInMainHand
                Bukkit.broadcastMessage(item.serializeToJson())
            }
            return true
        }
        else{
            val json = args.joinToString(" ")
            val item = json.deSerializeToItemStack()
            Bukkit.getOnlinePlayers().forEach {
                it.inventory.addItem(item)
            }
            return true
        }
    }
}
