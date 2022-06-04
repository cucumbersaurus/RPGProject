package project.rpg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import project.rpg.player.info.Skill;
import project.rpg.skill.magic.fire.MeteoStrike;
import project.rpg.skill.SkillDic;


public class SkillCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {

            Player player = (Player) sender;

            if ("add".equals(args[0])) {

                if(SkillDic.isExist(args[1])) {
                    player.sendMessage(args[1] + " is added ");
                    Skill.addSkill(player,SkillDic.getSkill(player,args[1]));
                } else {
                    sender.sendMessage("오타난 커멘드");
                }

            } else {
                player.sendMessage(command.getUsage());
            }
            return true;
        }
        return false;
    }
}
