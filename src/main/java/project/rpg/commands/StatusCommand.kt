package project.rpg.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import project.rpg.manager.AttributeManager
import project.rpg.player.info.Status

class StatusCommand : CommandExecutor {

    private lateinit var arg1:String
    private lateinit var arg2:String
    private lateinit var player:Player

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {

        if (sender is Player) {
            player = sender
            val uuid = player.uniqueId

            if ("add" == args[0] && args.size>=2) {
                this.arg1 = args[1]
                this.arg2 = args[2]

                val num = args[2].toInt()
                when (args[1]) {
                    "strength" -> {
                        sendFeedback(true)
                        Status.getPlayerMap()[uuid]!!.addStrength(num)
                    }
                    "agility" -> {
                        sendFeedback(true)
                        Status.getPlayerMap()[uuid]!!.addAgility(num)
                    }
                    "speed" -> {
                        sendFeedback(true)
                        Status.getPlayerMap()[uuid]!!.addSpeed(num)
                    }
                    "health" -> {
                        sendFeedback(true)
                        Status.getPlayerMap()[uuid]!!.addHealth(num)
                    }
                    "defense" -> {
                        sendFeedback(true)
                        Status.getPlayerMap()[uuid]!!.addDefense(num)
                    }
                    "luck" -> {
                        sendFeedback(true)
                        Status.getPlayerMap()[uuid]!!.addLuck(num)
                    }
                    "handicraft" -> {
                        sendFeedback(true)
                        Status.getPlayerMap()[uuid]!!.addHandicraft(num)
                    }
                    "attractive" -> {
                        sendFeedback(true)
                        Status.getPlayerMap()[uuid]!!.addAttractive(num)
                    }
                    else -> sendFeedback(false)
                }
                //jsonFile_.put(playerName, playerData_.get(playerName).getMap());
                AttributeManager.setAttributes(player, Status.getPlayerMap()[uuid])
            }
            else sendFeedback(false)
            return true
        }
        return false
    }

    private fun sendFeedback(isSuccess: Boolean){
        if(isSuccess) player.sendMessage("$arg1 is added $arg2")
        else player.sendMessage("오타난 커맨드")
    }
}