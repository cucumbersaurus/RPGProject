package project.rpg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import project.rpg.manager.AttributeManager;
import project.rpg.player.info.Status;
import project.rpg.skill.magic.fire.MeteoStrike;

import java.util.UUID;

import static project.rpg.player.info.Skill.skills;

public class SkillCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {

            Player player = (Player) sender;

            if ("add".equals(args[0])) {
                switch (args[1]) {
                    case "meteostrike":
                        player.sendMessage(args[1] + " is added ");
                        skills.get(player.getName()).addSkill(new MeteoStrike(player));
                        break;
                    default:
                        sender.sendMessage("오타난 커멘드");
                        break;
                }
            }
            return true;
        }
        return false;
    }
}
