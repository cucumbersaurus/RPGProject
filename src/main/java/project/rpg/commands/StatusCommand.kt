package project.rpg.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import project.rpg.manager.AttributeManager
import project.rpg.player.info.Status

class StatusCommand : CommandExecutor, TabCompleter {

    private lateinit var arg1:String
    private lateinit var arg2:String
    private lateinit var player:Player

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {

        if (sender is Player) {
            player = sender

            if ("add" == args[0] ) {
                if(args.size <= 2) {
                    sender.sendMessage(command.usage)
                    return true
                }
                this.arg1 = args[1]
                this.arg2 = args[2]


                val num = args[2].toInt()
                when (args[1]) {
                    "strength" -> {
                        sendFeedback(true)
                        Status.getPlayer(player).addStrength(num)
                    }
                    "agility" -> {
                        sendFeedback(true)
                        Status.getPlayer(player).addAgility(num)
                    }
                    "speed" -> {
                        sendFeedback(true)
                        Status.getPlayer(player).addSpeed(num)
                    }
                    "health" -> {
                        sendFeedback(true)
                        Status.getPlayer(player).addHealth(num)
                    }
                    "defense" -> {
                        sendFeedback(true)
                        Status.getPlayer(player).addDefense(num)
                    }
                    "luck" -> {
                        sendFeedback(true)
                        Status.getPlayer(player).addLuck(num)
                    }
                    "handicraft" -> {
                        sendFeedback(true)
                        Status.getPlayer(player).addHandicraft(num)
                    }
                    "intelligence" -> {
                        sendFeedback(true)
                        Status.getPlayer(player).addIntelligence(num)
                    }
                    else -> sendFeedback(false)
                }
                //jsonFile_.put(playerName, playerData_.get(playerName).getMap());
                AttributeManager.setAttributes(player, Status.getPlayer(player))
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

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>?
    ): MutableList<String> {
        val recommendation = ArrayList<String>()

        if(args != null){
            when(args.size){
                1 -> recommendation.add("add")
                2 -> {
                    recommendation.add("strength")
                    recommendation.add("agility")
                    recommendation.add("speed")
                    recommendation.add("health")
                    recommendation.add("defense")
                    recommendation.add("luck")
                    recommendation.add("handicraft")
                    recommendation.add("intelligence")
                }
            }
        }
        return recommendation
    }
}