package project.rpg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import project.rpg.player.info.Skill;
import project.rpg.skill.magic.fire.MeteoStrike;

public class SkillCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {

            Player player = (Player) sender;

            if ("add".equals(args[0])) {
                switch (args[1]) {
                    case "meteorStrike":
                        player.sendMessage(args[1] + " is added ");
                        Skill.setSkill(player, new MeteoStrike(player));
                        break;
                    default:
                        sender.sendMessage("오타난 커멘드");
                        break;
                }
            } else {
                player.sendMessage(command.getUsage());
            }
            return true;
        }
        return false;
    }
}