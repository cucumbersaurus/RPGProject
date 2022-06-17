package project.rpg.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import project.rpg.player.info.Skill
import project.rpg.skill.SkillDic

class SkillCommand : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if ("add" == args[0]) {
                if (args.size >= 2){
                    if (SkillDic.isExist(args[1])) {
                        Skill.addSkill(sender, SkillDic.makeSkill(sender, args[1]))
                        sender.sendMessage(args[1] + " is added ")
                    } else {
                        sender.sendMessage(command.usage)
                    }
            } else {
                sender.sendMessage(command.usage)
            }
        }
            return true
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

        if(args != null){
            when(args.size){
                1 -> recommendation.add("add")
                2 -> {
                    recommendation.addAll(SkillDic.getSkillList())
                }
            }
        }
        return recommendation
    }
}